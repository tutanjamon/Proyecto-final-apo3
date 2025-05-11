import java.util.Date;

public class Tarea {
    private String nombre;
    private String descripcion;
    private String prioridad;
    private String estado;
    private Date fechaLimite;

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public String getEstado() {
        return estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

