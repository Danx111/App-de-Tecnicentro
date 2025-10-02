package espol.poo.tecnicentroapp.Modelo.ServicioM;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioServicio implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "servicios.ser";
    private static ArrayList<Servicio> listaServicios = new ArrayList<>();

    /** Cargar desde archivo; si no existe, sembrar tus datos y persistir. */
    public static void cargarDatos() {
        ArrayList<Servicio> desdeDisco = leerDesdeArchivo();
        if (desdeDisco != null) {
            listaServicios = desdeDisco;
        } else {
            listaServicios = new ArrayList<>();
            listaServicios.add(new Servicio("Cambio de aceite", 20.0, "24-02-2025"));
            listaServicios.add(new Servicio("Alineación", 15.0, "24-02-2025"));
            listaServicios.add(new Servicio("Balanceo", 10.0, "24-02-2025"));
            listaServicios.add(new Servicio("Revisión eléctrica", 30.0, "24-02-2025"));
            listaServicios.add(new Servicio("Cambio de frenos", 40.0, "24-02-2025"));
            listaServicios.add(new Servicio("Limpieza de inyectores", 35.0, "24-02-2025"));
            guardarEnArchivo();
        }
    }

    /** Lista de solo lectura. */
    public static ArrayList<Servicio> getServicios() {
        return (listaServicios);
    }

    /** Agregar y persistir. */
    public static void agregarServicio(Servicio servicio) {
        if (servicio == null) return;
        listaServicios.add(servicio);
        guardarEnArchivo();
    }

    /** Buscar por nombre (ignorando mayúsculas). */
    public static Servicio getByNombre(String nombre) {
        if (nombre == null) return null;
        for (Servicio s : listaServicios) {
            if (nombre.equalsIgnoreCase(s.getNombre())) return s;
        }
        return null;
    }

    /** Buscar por código (si tu clase Servicio tiene getCodigo()). */
    public static Servicio getByCodigo(String codigo) {
        if (codigo == null) return null;
        for (Servicio s : listaServicios) {
            if (codigo.equalsIgnoreCase(s.getCodigo())) return s;
        }
        return null;
    }

    public static boolean actualizarPrecioPorCodigo(String codigo, double nuevoPrecio, String fecha) {
        Servicio s = getByCodigo(codigo);
        if (s == null) return false;
        s.setPrecio(nuevoPrecio);
        //s.cambiarPrecio(nuevoPrecio,fecha);
        guardarEnArchivo();
        return true;
    }

    private static void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(listaServicios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Servicio> leerDesdeArchivo() {
        File f = new File(FILE_PATH);
        if (!f.exists() || f.length() == 0) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                return (ArrayList<Servicio>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
