package espol.poo.tecnicentroapp.Modelo.TecnicosM;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repositorio de Técnicos con persistencia en archivo (.ser).
 * Requiere que Tecnico implemente java.io.Serializable.
 */
public class RepositorioTecnicos implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "tecnicos.ser";
    private static ArrayList<Tecnico> listaTecnicos = new ArrayList<>();

    public static void cargarDatos() {
        ArrayList<Tecnico> desdeDisco = leerDesdeArchivo();
        if (desdeDisco != null) {
            listaTecnicos = desdeDisco;
        } else {
            listaTecnicos = new ArrayList<>();
            listaTecnicos.add(new Tecnico("0912345678", "Álvaro López", "0987654321", "Mecánica general"));
            listaTecnicos.add(new Tecnico("0923456789", "Mario Barcos", "0976543210", "Sistemas de freno"));
            guardarEnArchivo(); // persistimos la siembra
        }
    }

    /** Lista de solo lectura para evitar mutaciones externas. */
    public static ArrayList<Tecnico> obtenerTecnicos() {
        return (listaTecnicos);
    }

    /** Agregar y persistir. */
    public static void agregarTecnico(Tecnico tecnico) {
        if (tecnico == null) return;
        listaTecnicos.add(tecnico);
        guardarEnArchivo();
    }

    /** Buscar por ID. */
    public static Tecnico getById(String id) {
        if (id == null) return null;
        for (Tecnico t : listaTecnicos) {
            if (id.equals(t.getIdentificacion())) {
                return t;
            }
        }
        return null;
    }

    /** Eliminar por ID y persistir. */
    public static boolean eliminarPorId(String id) {
        boolean removed = listaTecnicos.removeIf(
                t -> id != null && id.equals(t.getIdentificacion())
        );
        if (removed) guardarEnArchivo();
        return removed;
    }

    private static void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(listaTecnicos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Tecnico> leerDesdeArchivo() {
        File f = new File(FILE_PATH);
        if (!f.exists() || f.length() == 0) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                return (ArrayList<Tecnico>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
