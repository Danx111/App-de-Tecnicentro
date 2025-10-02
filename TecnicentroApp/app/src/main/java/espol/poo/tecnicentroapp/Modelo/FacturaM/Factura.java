package espol.poo.tecnicentroapp.Modelo.FacturaM;

import android.widget.Toast;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import espol.poo.tecnicentroapp.Modelo.ClienteM.*;
import espol.poo.tecnicentroapp.Modelo.OrdenesServicioM.*;
import espol.poo.tecnicentroapp.Modelo.ServicioM.*;

public class Factura implements Serializable {
    private Cliente cliente;
    private String periodoFactura;
    private String fechaCreacion;
    private final double cargoAdicional = 50.0;
    private double totalPagar;
    private double cargoFijo;
    private static int contador=0;
    private int codigo;
    private ArrayList<OrdenServicio> detalle;
    //Contructor clase Factura
    public Factura(Cliente cliente, String periodoFactura, ArrayList<OrdenServicio> ordenes) {
        contador++;
        this.codigo=contador;
        this.cliente = cliente;
        this.periodoFactura = periodoFactura;
        this.totalPagar = calcularTotal(ordenes);
        this.fechaCreacion = colocarFecha();
        this.detalle = ordenes;
        this.cargoFijo = cliente.getTipoCliente() == TipoCliente.EMPRESARIAL ? cargoAdicional : 0;
    }
    public String colocarFecha(){
        LocalDate hoy = LocalDate.now();
        String fechaFormateada = hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return fechaFormateada;
    }
    //metodo que calcula el total por cliente y en base al servicio
    public double calcularTotal(ArrayList<OrdenServicio> ordenes) {
        double total = 50.00;
        for (OrdenServicio o : ordenes) {
            if (o.getCliente().equals(cliente) &&
                o.perteneceAFactura(periodoFactura.split(" ")[0], periodoFactura.split(" ")[1])) {
                for (ItemServicio item : o.getItemsServicios()){
                    
                    total += item.calcularSubtotal();
                }
            }
        }
        return total + cargoFijo;
    }

    // Getters y setters
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public String getPeriodoFactura() {
        return periodoFactura;
    }
    public void setPeriodoFactura(String periodoFactura) {
        this.periodoFactura = periodoFactura;
    }
    public double getTotalPagar() {
        return totalPagar;
    }
    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }
    public double getCargoFijo() {
        return cargoFijo;
    }
    public void setCargoFijo(double cargoFijo) {
        this.cargoFijo = cargoFijo;
    }
    public int getCodigo(){
        return codigo;
    }
    public ArrayList<OrdenServicio> getOrdenes(){
        return detalle;
    }
    public String getFechaCreacion(){return fechaCreacion;}
}