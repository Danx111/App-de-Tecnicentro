package espol.poo.tecnicentroapp.Modelo.VehiculoM;

import java.util.ArrayList;

import espol.poo.tecnicentroapp.Modelo.ServicioM.Servicio;

public class RepositorioVehiculo {
    private static final ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

    public static void cargarDatos(){
        listaVehiculos.add(new Vehiculo("ABC-1234", TipoVehiculo.AUTOMOVIL)); //cliente personal
        listaVehiculos.add(new Vehiculo("MTR-5678", TipoVehiculo.MOTOCICLETA)); //cliente personal
        listaVehiculos.add(new Vehiculo("BUS-4455", TipoVehiculo.BUS));//cliente empresarial
        listaVehiculos.add(new Vehiculo("EMP-9090", TipoVehiculo.AUTOMOVIL));//cliente empresarial

    }

    public static ArrayList<Vehiculo> obtenerVehiculos() {
        return listaVehiculos;
    }

    public static void agregarCliente(Vehiculo vehiculo) {
        listaVehiculos.add(vehiculo);
    }

    public static Vehiculo getByPlaca(String placa) {
        for (Vehiculo v : listaVehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null; // Si no se encuentra
    }
}
