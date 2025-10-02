package espol.poo.tecnicentroapp.Modelo.ClienteM;

import java.io.Serializable;

import espol.poo.tecnicentroapp.Modelo.Usuario;

public class Cliente extends Usuario implements Serializable {
    private String direccion;
    private TipoCliente tipoCliente;
    //constructor cliente
    public Cliente(String identificacion, String nombre, String telefono, String direccion, TipoCliente tipoCliente) {
        super(identificacion, nombre, telefono);
        this.direccion = direccion;
        this.tipoCliente = tipoCliente;
    }
    //metodo boolean para identificar al cliente como EMPRESARIAL
    public boolean esEmpresarial() {
        return this.tipoCliente == TipoCliente.EMPRESARIAL;
    }
    //Getters - Setters
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    //Sobreescritura de metodo toString
    @Override
    public String toString() {
        return nombre;
    }

}