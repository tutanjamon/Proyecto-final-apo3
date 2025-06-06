package scr;

import java.util.*;

public class GestorTareas {
    private static List<Tarea> tareas = new ArrayList<>();
    private static final String ARCHIVO = "C:\\Users\\tutanjamon\\eclipse-workspace\\ProyectoApo2\\src\\data.txt";
    private static final GestorArchivos gestorArchivos = new GestorArchivos(ARCHIVO);

    // Cargar tareas desde el archivo usando el gestor de archivos
    public static void cargarDesdeArchivo() {
        tareas = gestorArchivos.cargarTareas();
        System.out.println("Tareas cargadas exitosamente desde el archivo.");
    }

    // Guardar las tareas actuales en el archivo
    public static void guardarEnArchivo() {
        gestorArchivos.guardarTareas(tareas);
    }

    // Mostrar la lista de tareas, ordenadas por plazo o prioridad según el parámetro
    public static void mostrarTareas(boolean porPrioridad) {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas.");
            return;
        }

        List<Tarea> copia = new ArrayList<>(tareas);

        if (porPrioridad) {
            copia.sort(Comparator.comparingInt(t -> t.prioridad));
        } else {
            copia.sort((t1, t2) -> {
                if (t1.dias != t2.dias) return Integer.compare(t1.dias, t2.dias);
                if (t1.horas != t2.horas) return Integer.compare(t1.horas, t2.horas);
                if (t1.minutos != t2.minutos) return Integer.compare(t1.minutos, t2.minutos);
                return Integer.compare(t1.prioridad, t2.prioridad);
            });
        }

        System.out.println("\n--- Lista de Tareas ---");
        for (int i = 0; i < copia.size(); i++) {
            System.out.println("\nTarea #" + (i + 1) + copia.get(i));
        }
    }

    // Obtener la lista actual de tareas
    public static List<Tarea> getTareas() {
        return tareas;
    }

    // Agregar una nueva tarea a la lista y guardar los cambios en archivo
    public static void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
        guardarEnArchivo();
    }

    // Editar una tarea existente en la lista por índice y guardar cambios
    public static void editarTarea(int index, Tarea nueva) {
        if (index >= 0 && index < tareas.size()) {
            tareas.set(index, nueva);
            guardarEnArchivo();
        }
    }

    // Eliminar una tarea de la lista por índice y guardar cambios
    public static void eliminarTarea(int index) {
        if (index >= 0 && index < tareas.size()) {
            tareas.remove(index);
            guardarEnArchivo();
        }
    }
}
