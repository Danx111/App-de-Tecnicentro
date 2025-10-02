package espol.poo.tecnicentroapp.Modelo.ServicioM;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.*;
import espol.poo.tecnicentroapp.Modelo.DatosM.*;
import espol.poo.tecnicentroapp.Modelo.TecnicosM.Tecnico;

public class Servicio implements Serializable {
    private String codigo;
    private String nombre;
    private double precio;
    private ArrayList<HistorialPrecio> historialPrecios;
    private static int contador = 0;
    //Constructor de la clase Servicio
    public Servicio(String nombre, double precio, String fecha) {
        contador++;
        this.codigo = String.format("%03d", contador);
        this.nombre = nombre;
        this.precio = precio;
        this.historialPrecios = new ArrayList<>();
        this.historialPrecios.add(new HistorialPrecio(precio, fecha, null));
    }
    //Metodoque cambia el precio del servicio registrando el cambio 
    public void cambiarPrecio(double nuevoPrecio, String fechaCambio) {
        historialPrecios.get(historialPrecios.size() - 1).setFechaFin(fechaCambio);
        historialPrecios.add(new HistorialPrecio(nuevoPrecio, fechaCambio, null));
        this.precio = nuevoPrecio;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public ArrayList<HistorialPrecio> getHistorialPrecios() {
        return historialPrecios;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Servicio)) return false;
        Servicio other = (Servicio) obj;
        return this.nombre.equals(other.nombre);
    }
    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
    public void setHistorialPrecios(ArrayList<HistorialPrecio> historialPrecios) {
        this.historialPrecios = historialPrecios;
    }
    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }
}
