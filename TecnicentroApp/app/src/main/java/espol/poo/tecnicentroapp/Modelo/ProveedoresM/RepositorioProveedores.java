package espol.poo.tecnicentroapp.Modelo.ProveedoresM;

import java.util.ArrayList;
import java.io.*;

public class RepositorioProveedores {
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "proveedores.ser";
    private static ArrayList<Proveedor> listaProveedores = new ArrayList<>();

    /** Cargar desde archivo (llamar una vez al iniciar la app). */
    public static void cargarDatos() {
        ArrayList<Proveedor> desdeDisco = leerDesdeArchivo();
        if (desdeDisco != null) {
            listaProveedores = desdeDisco;
        } else {
            listaProveedores = new ArrayList<>();
            listaProveedores.add(new Proveedor("PRV-001", "Autopartes Guayas", "0991112222","Repuestos y consumibles para mantenimiento"));
            listaProveedores.add(new Proveedor("P001", "Lubricadora Ecuador", "0991234567", "Proveedor de aceites y lubricantes"));
            guardarEnArchivo();
        }
    }

    /** Obtener lista como vista de solo lectura. */
    public static ArrayList<Proveedor> obtenerProveedores() {
        return (listaProveedores);
    }

    /** Agregar y persistir. */
    public static void agregarProveedor(Proveedor p) {
        if (p == null) return;
        listaProveedores.add(p);
        guardarEnArchivo();
    }

    /** Buscar por ID. */
    public static Proveedor getById(String id) {
        if (id == null) return null;
        for (Proveedor p : listaProveedores) {
            if (id.equals(p.getIdentificacion())) return p;
        }
        return null;
    }

    /** Eliminar por ID y persistir. */
    public static boolean eliminarPorId(String id) {
        boolean removed = listaProveedores.removeIf(
                p -> id != null && id.equals(p.getIdentificacion())
        );
        if (removed) guardarEnArchivo();
        return removed;
    }

    /* =================== Persistencia =================== */

    private static void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(listaProveedores);
        } catch (IOException e) {
            e.printStackTrace(); // o usa tu logger
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Proveedor> leerDesdeArchivo() {
        File f = new File(FILE_PATH);
        if (!f.exists() || f.length() == 0) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                return (ArrayList<Proveedor>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // o usa tu logger
        }
        return null;
    }
}
