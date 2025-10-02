package espol.poo.tecnicentroapp.Modelo.DatosM;

import espol.poo.tecnicentroapp.Modelo.ProveedoresM.*;

public class FaltaInsumo {
    private String descripcion;
    private Proveedor proveedor;
    //Contructor de la clase FaltaInsumo
    public FaltaInsumo(String descripcion, Proveedor proveedor) {
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }
    //Getters y setters
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Sobreescritura de toString que indica submenu de FaltaInsumo
    @Override
    public String toString() {
        return "Falta Insumo" +
                "\nDescripcion:" + descripcion + 
                "\nProveedor:" + proveedor;
    }
}