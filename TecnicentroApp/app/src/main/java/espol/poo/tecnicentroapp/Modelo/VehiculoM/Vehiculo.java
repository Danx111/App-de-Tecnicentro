package espol.poo.tecnicentroapp.Modelo.VehiculoM;

import java.io.Serializable;

public class Vehiculo implements Serializable {
    private String placa;
    private TipoVehiculo tipo;
    //Contructor de la clase Vehiculo
    public Vehiculo(String placa, TipoVehiculo tipo) {
        this.placa = placa;
        this.tipo = tipo;
    }
    //Getters and setters
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public TipoVehiculo getTipo() {
        return tipo;
    }
    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }
    //metodo sobreescrito toString que muestra la data de Vehiculo
    @Override
    public String toString() {
        return placa;
    }
}