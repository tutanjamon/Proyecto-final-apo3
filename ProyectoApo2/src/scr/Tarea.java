package scr;

public class Tarea {
    String nombre;
    String descripcion;
    int prioridad; 
    int estado;   
    int dias, horas, minutos; 

    // Constructor para crear una nueva tarea con todos sus atributos
    public Tarea(String nombre, String descripcion, int prioridad, int estado, int dias, int horas, int minutos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.dias = dias;
        this.horas = horas;
        this.minutos = minutos;
    }

    // Método que devuelve la prioridad en texto según el valor numérico
    public String getPrioridadTexto() {
        switch (prioridad) {
            case 1: return "Alta";
            case 2: return "Media";
            case 3: return "Baja";
            default: return "Desconocida";
        }
    }

    // Método que devuelve el estado en texto según el valor numérico
    public String getEstadoTexto() {
        return estado == 1 ? "Pendiente" : "Completada";
    }

    // Método que devuelve el plazo en formato de texto legible
    public String getPlazo() {
        return String.format("%d días, %d horas, %d minutos", dias, horas, minutos);
    }

    // Método que devuelve la representación completa de la tarea como texto
    public String toString() {
        return "\nNombre: " + nombre +
               "\nDescripción: " + descripcion +
               "\nPrioridad: " + getPrioridadTexto() +
               "\nEstado: " + getEstadoTexto() +
               "\nPlazo: " + getPlazo();
    }
}
