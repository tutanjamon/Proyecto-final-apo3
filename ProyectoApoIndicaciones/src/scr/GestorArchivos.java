package scr;

import java.io.*;
import java.util.*;

// Clase encargada de leer y escribir tareas en un archivo de texto
public class GestorArchivos {

    // Método para cargar las tareas desde un archivo
    public static List<Tarea> cargar(String ruta) {
        List<Tarea> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Se espera formato: "nombre";"descripcion";prioridad;estado;dd mm aaaa
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    String nombre = partes[0].replace("\"", "").trim();
                    String descripcion = partes[1].replace("\"", "").trim();
                    int prioridad = Integer.parseInt(partes[2].trim());
                    int estado = Integer.parseInt(partes[3].trim());

                    String[] fechaPartes = partes[4].trim().split(" ");
                    int dia = Integer.parseInt(fechaPartes[0]);
                    int mes = Integer.parseInt(fechaPartes[1]);
                    int anio = Integer.parseInt(fechaPartes[2]);

                    // Crear un objeto tarea con esos datos y agregar a la lista
                    lista.add(new Tarea(nombre, descripcion, prioridad, estado, dia, mes, anio));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }

        return lista;
    }

    // Método para guardar la lista completa de tareas en el archivo
    public static void guardar(String ruta, List<Tarea> tareas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Tarea t : tareas) {
                // Guardamos cada tarea en una línea con formato esperado
                bw.write("\"" + t.nombre + "\";\"" + t.descripcion + "\";" + t.prioridad + ";" + t.estado + ";" +
                         t.dia + " " + t.mes + " " + t.anio);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }
}
