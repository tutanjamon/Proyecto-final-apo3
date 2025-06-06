package scr;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Cargar tareas desde el archivo al iniciar el programa
        GestorTareas.cargarDesdeArchivo();

        int opcion;
        do {
            System.out.println("\n===== MENÚ =====");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Ver tareas ordenadas por plazo");
            System.out.println("3. Ver tareas ordenadas por prioridad");
            System.out.println("4. Editar tarea");
            System.out.println("5. Eliminar tarea");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1 -> agregarTarea();
                case 2 -> GestorTareas.mostrarTareas(false);
                case 3 -> GestorTareas.mostrarTareas(true);
                case 4 -> editarTarea();
                case 5 -> eliminarTarea();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 6);
    }

    // Función para agregar una nueva tarea a la lista
    static void agregarTarea() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Prioridad (1 = Alta, 2 = Media, 3 = Baja): ");
        int prioridad = scanner.nextInt();

        System.out.print("Estado (1 = Pendiente, 2 = Completada): ");
        int estado = scanner.nextInt();

        System.out.print("Plazo - días: ");
        int dias = scanner.nextInt();
        System.out.print("Plazo - horas: ");
        int horas = scanner.nextInt();
        System.out.print("Plazo - minutos: ");
        int minutos = scanner.nextInt();
        scanner.nextLine();
        
        Tarea nueva = new Tarea(nombre, descripcion, prioridad, estado, dias, horas, minutos);
        GestorTareas.agregarTarea(nueva);
        System.out.println("Tarea agregada correctamente.");
    }

    // Función para editar una tarea existente
    static void editarTarea() {
        if (GestorTareas.getTareas().isEmpty()) {
            System.out.println("No hay tareas para editar.");
            return;
        }

        GestorTareas.mostrarTareas(false);
        System.out.print("\nIngrese el número de la tarea a editar: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 

        if (index < 1 || index > GestorTareas.getTareas().size()) {
            System.out.println("Índice inválido.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nueva descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Nueva prioridad (1 = Alta, 2 = Media, 3 = Baja): ");
        int prioridad = scanner.nextInt();

        System.out.print("Nuevo estado (1 = Pendiente, 2 = Completada): ");
        int estado = scanner.nextInt();

        System.out.print("Nuevo plazo - días: ");
        int dias = scanner.nextInt();
        System.out.print("Nuevo plazo - horas: ");
        int horas = scanner.nextInt();
        System.out.print("Nuevo plazo - minutos: ");
        int minutos = scanner.nextInt();
        scanner.nextLine(); 

        Tarea nueva = new Tarea(nombre, descripcion, prioridad, estado, dias, horas, minutos);
        GestorTareas.editarTarea(index - 1, nueva);
        System.out.println("Tarea actualizada.");
    }

    // Función para eliminar una tarea existente
    static void eliminarTarea() {
        if (GestorTareas.getTareas().isEmpty()) {
            System.out.println("No hay tareas para eliminar.");
            return;
        }

        GestorTareas.mostrarTareas(false);
        System.out.print("\nIngrese el número de la tarea a eliminar: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 

        if (index < 1 || index > GestorTareas.getTareas().size()) {
            System.out.println("Índice inválido.");
            return;
        }

        GestorTareas.eliminarTarea(index - 1);
        System.out.println("Tarea eliminada.");
    }
}
