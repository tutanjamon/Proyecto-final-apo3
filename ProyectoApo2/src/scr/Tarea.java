package scr;


 
public class Tarea {
    String nombre;
    String descripcion;
    int prioridad; 
    int estado;   
    int dias, horas, minutos; 

   
    public Tarea(String nombre, String descripcion, int prioridad, int estado, int dias, int horas, int minutos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.dias = dias;
        this.horas = horas;
        this.minutos = minutos;
    }

  
    public String getPrioridadTexto() {
        switch (prioridad) {
            case 1: return "Alta";
            case 2: return "Media";
            case 3: return "Baja";
            default: return "Desconocida";
        }
    }

    
    public String getEstadoTexto() {
        return estado == 1 ? "Pendiente" : "Completada";
    }

    
    public String getPlazo() {
        return String.format("%d días, %d horas, %d minutos", dias, horas, minutos);
    }

    
    public String toString() {
        return "\nNombre: " + nombre +
               "\nDescripción: " + descripcion +
               "\nPrioridad: " + getPrioridadTexto() +
               "\nEstado: " + getEstadoTexto() +
               "\nPlazo: " + getPlazo();
    }
}

