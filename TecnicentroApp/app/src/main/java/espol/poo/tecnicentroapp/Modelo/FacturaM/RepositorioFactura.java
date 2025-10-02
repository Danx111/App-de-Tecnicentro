package espol.poo.tecnicentroapp.Modelo.FacturaM;
import java.io.*;
import java.util.ArrayList;

import espol.poo.tecnicentroapp.Modelo.ClienteM.Cliente;

public class RepositorioFactura implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String FILE_PATH = "facturas.ser";
    private static ArrayList<Factura> listaFacturas = new ArrayList<>();

    /** Cargar desde archivo; si no existe, iniciar vacío y persistir. */
    public static void cargarDatos() {
        ArrayList<Factura> desdeDisco = leerDesdeArchivo();
        if (desdeDisco != null) {
            listaFacturas = desdeDisco;
        } else {
            listaFacturas = new ArrayList<>();
            guardarEnArchivo();
        }
    }

    /** Vista de solo lectura para UI. */
    public static ArrayList<Factura> obtenerFacturas() {
        return (listaFacturas);
    }

    /** Agregar y persistir. */
    public static void agregarFactura(Factura factura) {
        if (factura == null) return;
        listaFacturas.add(factura);
        guardarEnArchivo();
    }

    public static Factura getByCodigo(int codigo){
        for(Factura f : listaFacturas){
            if(f.getCodigo()==codigo){
                return f;
            }
        }return null;
    }

    /** Buscar por cliente y periodo (ej. "2025-06" o "Junio 2025" según tu app). */
    /*public static List<Factura> buscarPorClienteYPeriodo(Cliente cliente, String periodo) {
        ArrayList<Factura> res = new ArrayList<>();
        if (cliente == null || periodo == null) return res;
        for (Factura f : listaFacturas) {
            if (cliente.equals(f.getCliente()) &&
                    periodo.equalsIgnoreCase(f.getPeriodoFactura())) {
                res.add(f);
            }
        }
        return res;
    }*/

    /** Eliminar por instancia y persistir. */
    /*public static boolean eliminar(Factura factura) {
        boolean removed = listaFacturas.remove(factura);
        if (removed) guardarEnArchivo();
        return removed;
    }*/

    /* -------------------------------------------------------
       Si tu clase Factura tiene un identificador (p.ej. getNumero()
       o getCodigo()), puedes habilitar esta variante:
    -------------------------------------------------------- */
    public static boolean eliminarPorIdentificador(String id) {
        if (id == null) return false;
        boolean removed = listaFacturas.removeIf(f -> {
            try {
                // Ajusta el nombre del método según tu modelo: getNumero(), getCodigo(), etc.
                String value =
                        (String) f.getClass().getMethod("getCodigo").invoke(f);
                return id.equalsIgnoreCase(value);
            } catch (Exception ignore) {
                return false;
            }
        });
        if (removed) guardarEnArchivo();
        return removed;
    }

    /* =================== Persistencia =================== */

    private static void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(listaFacturas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Factura> leerDesdeArchivo() {
        File f = new File(FILE_PATH);
        if (!f.exists() || f.length() == 0) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) return (ArrayList<Factura>) obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
