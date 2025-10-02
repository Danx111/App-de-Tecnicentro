package espol.poo.tecnicentroapp.Modelo.TecnicosM;

public class ReporteTecnico {
    private Tecnico tecnico;
    private double total;

    public ReporteTecnico(Tecnico tecnico, double total) {
        this.tecnico = tecnico;
        this.total = total;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

