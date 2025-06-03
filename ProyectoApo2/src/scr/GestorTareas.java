package scr;

import java.util.*;
import java.io.*;

public class GestorTareas {
    static List<Tarea> tareas = new ArrayList<>();
    static final String ARCHIVO = "C:\\Users\\tutanjamon\\eclipse-workspace\\ProyectoApo2\\src\\data.txt";

   
    public static void cargarDesdeArchivo() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(ARCHIVO));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 7) {
                    String nombre = partes[0].replace("\"", "");
                    String descripcion = partes[1].replace("\"", "");
                    int prioridad = Integer.parseInt(partes[2]);
                    int estado = Integer.parseInt(partes[3]);
                    int dias = Integer.parseInt(partes[4]);
                    int horas = Integer.parseInt(partes[5]);
                    int minutos = Integer.parseInt(partes[6]);
                    tareas.add(new Tarea(nombre, descripcion, prioridad, estado, dias, horas, minutos));
                }
            }
            System.out.println("Tareas cargadas exitosamente desde el archivo.");
        } catch (Exception e) {
            System.out.println("No se pudo cargar el archivo: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }

    
    public static void guardarEnArchivo() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(ARCHIVO));
            for (Tarea t : tareas) {
                writer.write(String.format("\"%s\";\"%s\";%d;%d;%d;%d;%d\n",
                        t.nombre, t.descripcion, t.prioridad, t.estado, t.dias, t.horas, t.minutos));
            }
        } catch (IOException e) {
            System.out.println("No se pudo guardar en el archivo: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }

  
    public static void mostrarTareas(boolean porPrioridad) {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas.");
            return;
        }

        List<Tarea> copia = new ArrayList<Tarea>(tareas);

        if (porPrioridad) {
            Collections.sort(copia, new Comparator<Tarea>() {
                public int compare(Tarea t1, Tarea t2) {
                    return Integer.compare(t1.prioridad, t2.prioridad);
                }
            });
        } else {
            Collections.sort(copia, new Comparator<Tarea>() {
                public int compare(Tarea t1, Tarea t2) {
                    if (t1.dias != t2.dias)
                        return Integer.compare(t1.dias, t2.dias);
                    if (t1.horas != t2.horas)
                        return Integer.compare(t1.horas, t2.horas);
                    if (t1.minutos != t2.minutos)
                        return Integer.compare(t1.minutos, t2.minutos);
                    return Integer.compare(t1.prioridad, t2.prioridad);
                }
            });
        }

        System.out.println("\n--- Lista de Tareas ---");
        for (int i = 0; i < copia.size(); i++) {
            System.out.println("\nTarea #" + (i + 1) + copia.get(i));
        }
    }

    public static List<Tarea> getTareas() {
        return tareas;
    }

    public static void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
        guardarEnArchivo();
    }

    public static void editarTarea(int index, Tarea nueva) {
        if (index >= 0 && index < tareas.size()) {
            tareas.set(index, nueva);
            guardarEnArchivo();
        }
    }

    public static void eliminarTarea(int index) {
        if (index >= 0 && index < tareas.size()) {
            tareas.remove(index);
            guardarEnArchivo();
        }
    }
}
