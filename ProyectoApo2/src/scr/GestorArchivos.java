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
                if (partes.length != 5) continue; 

                String nombre = partes[0].replaceAll("\"", "");
                String descripcion = partes[1].replaceAll("\"", "");
                int prioridad = convertirPrioridad(partes[2]);
                int estado = partes[3].trim().equalsIgnoreCase("Pendiente") ? 1 : 2;

                String[] tiempo = partes[4].trim().split(" ");
                int dias = Integer.parseInt(tiempo[0]);
                int horas = Integer.parseInt(tiempo[1]);
                int minutos = Integer.parseInt(tiempo[2]);

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
                pw.println(t.toArchivo()); 
            }
        } catch (IOException e) {
            System.out.println("Error al guardar tareas: " + e.getMessage());
        }
    }


    private int convertirPrioridad(String texto) {
        texto = texto.trim().toLowerCase();
        return switch (texto) {
            case "alta" -> 1;
            case "media" -> 2;
            case "baja" -> 3;
            default -> 3;
        };
    }
}
