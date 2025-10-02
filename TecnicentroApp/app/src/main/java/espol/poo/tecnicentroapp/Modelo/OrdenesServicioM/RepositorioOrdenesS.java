package espol.poo.tecnicentroapp.Modelo.OrdenesServicioM;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import espol.poo.tecnicentroapp.Modelo.ClienteM.Cliente;
import espol.poo.tecnicentroapp.Modelo.ClienteM.RepositorioCliente;
import espol.poo.tecnicentroapp.Modelo.ClienteM.TipoCliente;
import espol.poo.tecnicentroapp.Modelo.ServicioM.*;
import espol.poo.tecnicentroapp.Modelo.TecnicosM.*;
import espol.poo.tecnicentroapp.Modelo.VehiculoM.RepositorioVehiculo;
import espol.poo.tecnicentroapp.Modelo.VehiculoM.Vehiculo;

public class RepositorioOrdenesS implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String FILE_PATH = "ordenes.ser";
    private static ArrayList<OrdenServicio> listaOrdenServicio = new ArrayList<>();

    /** Cargar desde archivo; si no existe, sembrar tus datos y persistir. */
    public static void cargarDatos() {
        ArrayList<OrdenServicio> desdeDisco = leerDesdeArchivo();
        if (desdeDisco != null) {
            listaOrdenServicio = desdeDisco;
            return;
        }

        // ======= Siembra inicial (igual que tu versión previa) =======
        Cliente c1 = RepositorioCliente.getById("001");
        Cliente c2 = RepositorioCliente.getById("002");
        Cliente c3 = RepositorioCliente.getById("003");
        Cliente c4 = RepositorioCliente.getById("004");

        Tecnico tec1 = RepositorioTecnicos.getById("0912345678"); // Álvaro López
        Tecnico tec2 = RepositorioTecnicos.getById("0923456789"); // Mario Barcos

        Vehiculo vParticular1 = RepositorioVehiculo.getByPlaca("ABC-1234");
        Vehiculo vParticular2 = RepositorioVehiculo.getByPlaca("MTR-5678");
        Vehiculo vEmpresa1    = RepositorioVehiculo.getByPlaca("EMP-9090");
        Vehiculo vEmpresa2    = RepositorioVehiculo.getByPlaca("BUS-4455");

        Servicio sAceite      = RepositorioServicio.getByNombre("Cambio de aceite");
        Servicio sAlineacion  = RepositorioServicio.getByNombre("Alineación");
        Servicio sBalanceo    = RepositorioServicio.getByNombre("Balanceo");
        Servicio sElectrica   = RepositorioServicio.getByNombre("Revisión eléctrica");
        Servicio sFrenos      = RepositorioServicio.getByNombre("Cambio de frenos");
        Servicio sInyectores  = RepositorioServicio.getByNombre("Limpieza de inyectores");

        OrdenServicio o1 = new OrdenServicio(c1, vParticular1, "15/07/2025", tec1);
        o1.agregarItemServicio(new ItemServicio(sAceite, 1));
        o1.agregarItemServicio(new ItemServicio(sAlineacion, 2));

        OrdenServicio o2 = new OrdenServicio(c2, vEmpresa1, "20/07/2025", tec1);
        o2.agregarItemServicio(new ItemServicio(sFrenos, 2));
        o2.agregarItemServicio(new ItemServicio(sBalanceo, 1));

        OrdenServicio o3 = new OrdenServicio(c3, vParticular2, "05/08/2025", tec2);
        o3.agregarItemServicio(new ItemServicio(sElectrica, 1));
        o3.agregarItemServicio(new ItemServicio(sAceite, 1));

        OrdenServicio o4 = new OrdenServicio(c4, vEmpresa2, "10/08/2025", tec2);
        o4.agregarItemServicio(new ItemServicio(sInyectores, 1));
        o4.agregarItemServicio(new ItemServicio(sAlineacion, 1));

        listaOrdenServicio = new ArrayList<>();
        listaOrdenServicio.add(o1);
        listaOrdenServicio.add(o2);
        listaOrdenServicio.add(o3);
        listaOrdenServicio.add(o4);
        // ============================================================

        guardarEnArchivo(); // persistimos la siembra
    }

    /** Vista de solo lectura para la UI. */
    public static ArrayList<OrdenServicio> obtenerOrdenes() {
        return (listaOrdenServicio);
    }

    /** Agregar y persistir. */
    public static void agregarOrden(OrdenServicio orden) {
        if (orden == null) return;
        listaOrdenServicio.add(orden);
        guardarEnArchivo();
    }

    public static OrdenServicio getByCodigo(int codigo){
        for(OrdenServicio o : listaOrdenServicio) {
        if(o.getCodigo() == codigo){
            return o;
            }
        }return null;
    }

    /* =================== Reportes (sin cambios) =================== */

    public static ArrayList<ReporteTecnico> reportePorTecnico(String mes, String anio) {
        ArrayList<ReporteTecnico> lista = new ArrayList<>();
        for (OrdenServicio o : listaOrdenServicio) {
            if (coincideMesAnio(o.getFecha(), mes, anio)) {
                Tecnico t = o.getTecnico();
                double total = calcularTotalOrden(o.getItemsServicios());
                boolean encontrado = false;
                for (ReporteTecnico rt : lista) {
                    if (rt.getTecnico().equals(t)) {
                        rt.setTotal(rt.getTotal() + total);
                        encontrado = true;
                    }
                }
                if (!encontrado) lista.add(new ReporteTecnico(t, total));
            }
        }
        return lista;
    }

    public static ArrayList<ReporteServicio> reportePorServicio(String mes, String anio) {
        ArrayList<ReporteServicio> lista = new ArrayList<>();
        for (OrdenServicio o : listaOrdenServicio) {
            if (coincideMesAnio(o.getFecha(), mes, anio)) {
                for (ItemServicio it : o.getItemsServicios()) {
                    Servicio s = it.getServicio();
                    double total = it.getSubtotal();
                    boolean encontrado = false;
                    for (ReporteServicio rs : lista) {
                        if (rs.getServicio().equals(s)) {
                            rs.setTotal(rs.getTotal() + total);
                            encontrado = true;
                        }
                    }
                    if (!encontrado) lista.add(new ReporteServicio(s, total));
                }
            }
        }
        return lista;
    }

    public static boolean coincideMesAnio(String fecha, String mes, String anio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(fecha, formatter);
        return date.getMonthValue() == Integer.parseInt(mes)
                && date.getYear() == Integer.parseInt(anio);
    }

    public static double calcularTotalOrden(ArrayList<ItemServicio> items) {
        double total = 0.0;
        for (ItemServicio it : items) total += it.getSubtotal();
        return total;
    }

    /* =================== Persistencia =================== */

    private static void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(listaOrdenServicio);
        } catch (IOException e) {
            e.printStackTrace(); // usa tu logger si tienes
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<OrdenServicio> leerDesdeArchivo() {
        File f = new File(FILE_PATH);
        if (!f.exists() || f.length() == 0) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) return (ArrayList<OrdenServicio>) obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<ItemServicio> ordenesEmpresa(String identificacion, String mes, String anio){
        ArrayList<ItemServicio> lista = new ArrayList<>();
        for(OrdenServicio o : listaOrdenServicio){
            if(o.getCliente().getIdentificacion()== identificacion){
                if(coincideMesAnio(o.getFecha(),mes,anio)){
                    lista.addAll(o.getItemsServicios());
                }
            }
        }return lista;
    }
    public static ArrayList<OrdenServicio> detalleOrdenes(String identificacion, String mes, String anio){
        ArrayList<OrdenServicio> lista = new ArrayList<>();
        for(OrdenServicio o : listaOrdenServicio){
            if(o.getCliente().getIdentificacion()== identificacion){
                if(coincideMesAnio(o.getFecha(),mes,anio)){
                    lista.add(o);
                }
            }
        }return lista;
    }

}
