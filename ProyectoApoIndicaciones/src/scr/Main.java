package scr;

import java.util.*;

// Clase principal que contiene el menú y la lógica para manejar las tareas
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Tarea> tareas = new ArrayList<>();
    // Ruta del archivo donde se almacenan las tareas
    static final String RUTA_ARCHIVO = "C:\\Users\\tutanjamon\\eclipse-workspace\\ProyectoApo2\\src\\data.txt";

    public static void main(String[] args) {
        // Cargar tareas automáticamente al iniciar
        tareas = GestorArchivos.cargar(RUTA_ARCHIVO);

        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar salto de línea

            switch (opcion) {
                case 1 -> agregarTarea();
                case 2 -> mostrarTareas(false);
                case 3 -> mostrarTareas(true);
                case 4 -> editarTarea();
                case 5 -> eliminarTarea();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    // Muestra las opciones del menú
    static void mostrarMenu() {
        System.out.println("\n===== MENÚ =====");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Ver tareas ordenadas por fecha");
        System.out.println("3. Ver tareas ordenadas por prioridad");
        System.out.println("4. Editar tarea");
        System.out.println("5. Eliminar tarea");
        System.out.println("6. Salir");
        System.out.print("Opción: ");
    }

    // Solicita datos para crear una nueva tarea, la agrega y guarda en archivo
    static void agregarTarea() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Prioridad (1 = Alta, 2 = Media, 3 = Baja): ");
        int prioridad = scanner.nextInt();

        System.out.print("Estado (1 = Pendiente, 2 = Completada): ");
        int estado = scanner.nextInt();

        System.out.print("Fecha límite (DD MM AAAA): ");
        int dia = scanner.nextInt();
        int mes = scanner.nextInt();
        int anio = scanner.nextInt();
        scanner.nextLine(); // limpiar

        tareas.add(new Tarea(nombre, descripcion, prioridad, estado, dia, mes, anio));
        // Guardar cambios inmediatamente
        GestorArchivos.guardar(RUTA_ARCHIVO, tareas);
        System.out.println("Tarea agregada y guardada correctamente.");
    }

    // Muestra las tareas ordenadas según la opción (por prioridad o fecha)
    static void mostrarTareas(boolean porPrioridad) {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas.");
            return;
        }

        List<Tarea> copia = new ArrayList<>(tareas);

        if (porPrioridad) {
            copia.sort(Comparator.comparingInt(t -> t.prioridad));
        } else {
            copia.sort(Comparator
                    .comparingInt((Tarea t) -> t.anio)
                    .thenComparingInt(t -> t.mes)
                    .thenComparingInt(t -> t.dia)
                    .thenComparingInt(t -> t.prioridad));
        }

        System.out.println("\n--- Lista de Tareas ---");
        for (int i = 0; i < copia.size(); i++) {
            System.out.println("\nTarea #" + (i + 1) + copia.get(i));
        }
    }

    // Permite al usuario editar una tarea seleccionada y guarda los cambios
    static void editarTarea() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas para editar.");
            return;
        }

        mostrarTareas(false);
        System.out.print("\nIngrese el número de la tarea a editar: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // limpiar

        if (index < 1 || index > tareas.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Tarea t = tareas.get(index - 1);

        System.out.print("Nuevo nombre (dejar vacío para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) t.nombre = nombre;

        System.out.print("Nueva descripción (dejar vacío para no cambiar): ");
        String descripcion = scanner.nextLine();
        if (!descripcion.isEmpty()) t.descripcion = descripcion;

        System.out.print("Nueva prioridad (1 = Alta, 2 = Media, 3 = Baja): ");
        int prio = scanner.nextInt();
        if (prio >= 1 && prio <= 3) t.prioridad = prio;

        System.out.print("Nuevo estado (1 = Pendiente, 2 = Completada): ");
        int estado = scanner.nextInt();
        if (estado == 1 || estado == 2) t.estado = estado;

        System.out.print("Nueva fecha (DD MM AAAA): ");
        t.dia = scanner.nextInt();
        t.mes = scanner.nextInt();
        t.anio = scanner.nextInt();
        scanner.nextLine(); // limpiar

        // Guardar cambios inmediatamente
        GestorArchivos.guardar(RUTA_ARCHIVO, tareas);
        System.out.println("Tarea editada y guardada correctamente.");
    }

    // Permite eliminar una tarea seleccionada y guarda los cambios
    static void eliminarTarea() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas para eliminar.");
            return;
        }

        mostrarTareas(false);
        System.out.print("\nIngrese el número de la tarea a eliminar: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // limpiar

        if (index < 1 || index > tareas.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        tareas.remove(index - 1);
        // Guardar cambios inmediatamente
        GestorArchivos.guardar(RUTA_ARCHIVO, tareas);
        System.out.println("Tarea eliminada y cambios guardados.");
    }
}
