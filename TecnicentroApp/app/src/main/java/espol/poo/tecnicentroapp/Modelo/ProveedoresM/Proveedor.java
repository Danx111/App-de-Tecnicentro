package espol.poo.tecnicentroapp.Modelo.ProveedoresM;

import java.io.Serializable;

import espol.poo.tecnicentroapp.Modelo.Usuario;

public class Proveedor extends Usuario implements Serializable {
    private String descripcion;
    //Constructor de la clase Provedor
    public Proveedor(String id, String nombre, String telefono, String descripcion) {
        super(id, nombre, telefono);
        this.descripcion = descripcion;
    }
    //Getters y setters
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    //Sobreescritura del metodo toString que identifica a los provedores
    @Override
    public String toString() {
        return  "Identificacion: " + getIdentificacion()  +
                "\nNombre: " + getNombre() +
                "\nTelefono: " + getTelefono() +
                "\nDescripcion: " + descripcion + '\n' 
                ;
    }
}