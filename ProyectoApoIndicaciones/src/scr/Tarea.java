package scr;

// Clase que representa una tarea con sus atributos y métodos auxiliares
class Tarea {
    String nombre;
    String descripcion;
    int prioridad; // 1 = Alta, 2 = Media, 3 = Baja
    int estado;    // 1 = Pendiente, 2 = Completada
    int dia, mes, anio;  // Fecha límite de la tarea

    // Constructor para inicializar la tarea
    public Tarea(String nombre, String descripcion, int prioridad, int estado, int dia, int mes, int anio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    // Devuelve el texto correspondiente a la prioridad numérica
    public String getPrioridadTexto() {
        return switch (prioridad) {
            case 1 -> "Alta";
            case 2 -> "Media";
            case 3 -> "Baja";
            default -> "Desconocida";
        };
    }

    // Devuelve el texto correspondiente al estado numérico
    public String getEstadoTexto() {
        return estado == 1 ? "Pendiente" : "Completada";
    }

    // Devuelve la fecha límite en formato dd/mm/aaaa
    public String getFecha() {
        return String.format("%02d/%02d/%04d", dia, mes, anio);
    }

    // Representación en texto de la tarea (para imprimirla en consola)
    public String toString() {
        return "\nNombre: " + nombre +
               "\nDescripción: " + descripcion +
               "\nPrioridad: " + getPrioridadTexto() +
               "\nEstado: " + getEstadoTexto() +
               "\nFecha límite: " + getFecha();
    }
}
