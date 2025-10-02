package espol.poo.tecnicentroapp.Modelo.ServicioM;

public class ItemServicio {
    private int cantidad;
    private double subtotal;
    private Servicio servicio;
    //Constructor de la clase ItemServicio
    public ItemServicio(Servicio servicio, int cantidad) {
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }
    //metodo que calcula el valor del servicio x la cantidad de veces que se lo realiza
    public double calcularSubtotal() {
        return servicio.getPrecio() * cantidad;
    }
    //Getters y setters
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    public Servicio getServicio() {
        return servicio;
    }
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
        this.subtotal = calcularSubtotal();
    }
    //Metodo toString sobreescrito que muestra el total por cada serviciorealizado
    @Override
    public String toString() {
        return "ItemServicio{" +
                "cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", servicio=" + servicio +
                '}';
    }
    
}