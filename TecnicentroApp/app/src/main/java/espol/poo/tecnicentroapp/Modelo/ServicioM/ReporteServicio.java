package espol.poo.tecnicentroapp.Modelo.ServicioM;

public class ReporteServicio {
    private Servicio servicio;
    private double total;
    public ReporteServicio(Servicio servicio, double total){
        this.servicio=servicio;
        this.total=total;
    }
    public Servicio getServicio(){
        return servicio;
    }
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
