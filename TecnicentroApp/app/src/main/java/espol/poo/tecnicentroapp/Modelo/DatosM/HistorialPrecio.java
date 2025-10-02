package espol.poo.tecnicentroapp.Modelo.DatosM;

public class HistorialPrecio {
    private double precio;
    private String fechaInicio;
    private String fechaFin;
    //Contructor de la clase HistorialPrecio
    public HistorialPrecio(double precio, String fechaInicio, String fechaFin) {
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    //Getters y setters
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    //Sobreescritura de toString sobre HistorialPrecio
    @Override
    public String toString() {
        return "HistorialPrecio{" +
                "precio=" + precio +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                '}';
    }
}
