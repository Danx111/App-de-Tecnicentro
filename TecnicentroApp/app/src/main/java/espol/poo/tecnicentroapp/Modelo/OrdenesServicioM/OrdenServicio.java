package espol.poo.tecnicentroapp.Modelo.OrdenesServicioM;

import java.io.Serializable;
import java.util.ArrayList;
import espol.poo.tecnicentroapp.Modelo.ClienteM.*;
import espol.poo.tecnicentroapp.Modelo.VehiculoM.*;
import espol.poo.tecnicentroapp.Modelo.TecnicosM.*;
import espol.poo.tecnicentroapp.Modelo.ServicioM.*;



public class OrdenServicio implements Serializable {
    private Cliente cliente;
    private Vehiculo vehiculo;
    private String fecha;
    private ArrayList<ItemServicio> serviciosOS = new ArrayList<>();
    private Tecnico tecnico;
    private static int contador=0;
    private int codigo;
    
    //constructor de la clase OrdenServicio
    public OrdenServicio(Cliente cliente, Vehiculo vehiculo, String fecha, Tecnico tecnico) {
        contador++;
        this.codigo=contador;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fecha = fecha;
        this.tecnico = tecnico;
    }
    //Se agrega el item a la lista servicios
    public void agregarItemServicio(ItemServicio item) {
        serviciosOS.add(item);
    }
    //verifica la fecha de facturacion
    public boolean perteneceAFactura(String mes, String anio) {
        return fecha.contains(mes) && fecha.contains(anio);
    }
    //metodo que retorna el total de los servicios a os clientes empresariales
    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < serviciosOS.size(); i++) {
            ItemServicio item = serviciosOS.get(i);
            total += item.calcularSubtotal();
        }

        //SI SE DESCOMENTA SE AUMENTA 50 EN ORDEN SERVICIO
        /*if (cliente.getTipoCliente().equals(TipoCliente.EMPRESARIAL)){
            total+= 50.0;
    }*/
        return total;
    }
    // Getters y Setters
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public ArrayList<ItemServicio> getItemsServicios() {
        return (ArrayList<ItemServicio>) serviciosOS;
    }
    public int getCodigo(){
        return codigo;
    }
    public Tecnico getTecnico() {
        return tecnico;
    }
    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }
    //metodo toString que muestra la orden delservicio
    @Override
    public String toString() {  
        StringBuilder sb = new StringBuilder();
        sb.append("OrdenServicio{")
          .append("cliente=").append(cliente)
          .append(", vehiculo=").append(vehiculo)
          .append(", fecha='").append(fecha).append('\'')
          .append(", servicios=").append(serviciosOS)
          .append(", tecnico=").append(tecnico)
          .append('}');
        return sb.toString();
    }
}