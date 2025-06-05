package scr;

import java.io.*;
import java.util.*;

public class GestorArchivos {
    private final String rutaArchivo;

    public GestorArchivos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public List<Tarea> cargarTareas() {
        List<Tarea> lista = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("El archivo no existe. Se creará uno nuevo al guardar.");
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length != 7) continue;

                String nombre = partes[0].replace("\"", "");
                String descripcion = partes[1].replace("\"", "");
                int prioridad = Integer.parseInt(partes[2]);
                int estado = Integer.parseInt(partes[3]);
                int dias = Integer.parseInt(partes[4]);
                int horas = Integer.parseInt(partes[5]);
                int minutos = Integer.parseInt(partes[6]);

                lista.add(new Tarea(nombre, descripcion, prioridad, estado, dias, horas, minutos));
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return lista;
    }

    public void guardarTareas(List<Tarea> tareas) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
            for (Tarea t : tareas) {
                pw.printf("\"%s\";\"%s\";%d;%d;%d;%d;%d\n",
                          t.nombre, t.descripcion, t.prioridad, t.estado, t.dias, t.horas, t.minutos);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar tareas: " + e.getMessage());
        }
    }
}

