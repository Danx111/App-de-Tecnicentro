package espol.poo.tecnicentroapp.Modelo.ClienteM;

import java.io.*;
import java.util.ArrayList;

public class RepositorioCliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "clientes.ser";
    private static ArrayList<Cliente> listaClientes = new ArrayList<>();

    /** Carga la lista desde el archivo. Si no existe, siembra datos y persiste. */
    public static void cargarDatos() {
        ArrayList<Cliente> desdeDisco = leerDesdeArchivo();
        if (desdeDisco != null) {
            listaClientes = desdeDisco;
        } else {
            // Semilla inicial (opcional)
            listaClientes = new ArrayList<>();
            listaClientes.add(new Cliente("001", "Carlos Pérez", "Calle 123", "555-1234", TipoCliente.PERSONAL));
            listaClientes.add(new Cliente("002", "Empresa XYZ", "Av. Central 45", "555-6789", TipoCliente.EMPRESARIAL));
            listaClientes.add(new Cliente("003", "Ana López", "Cra 45 #67", "555-9999", TipoCliente.PERSONAL));
            listaClientes.add(new Cliente("004", "Empresa Tecnicentro", "Av. Quito local 31", "04-2606351", TipoCliente.EMPRESARIAL));
            guardarEnArchivo(); // persistimos la semilla
        }
    }

    /** Devuelve una vista inmodificable de los clientes para evitar mutaciones externas. */
    public static ArrayList<Cliente> obtenerClientes() {
        return listaClientes;
    }

    public static ArrayList<Cliente> obtenerClientesEmpresa() {
        ArrayList<Cliente> listaEmpresas = new ArrayList<>();
        for(Cliente c : listaClientes){
            if(c.getTipoCliente()==TipoCliente.EMPRESARIAL){
                listaEmpresas.add(c);
            }
        }
        return listaEmpresas;
    }

    /** Agrega y persiste. */
    public static void agregarCliente(Cliente cliente) {
        if (cliente == null) return;
        listaClientes.add(cliente);
        guardarEnArchivo();
    }

    /** Busca en memoria (la memoria siempre refleja el archivo tras cargar/guardar). */
    public static Cliente getById(String id) {
        if (id == null) return null;
        for (Cliente c : listaClientes) {
            if (id.equals(c.getIdentificacion())) {
                return c;
            }
        }
        return null;
    }

    /** ---- Persistencia ---- */

    private static void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(listaClientes);
        } catch (IOException e) {
            e.printStackTrace(); // Puedes cambiar a tu logger
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Cliente> leerDesdeArchivo() {
        File f = new File(FILE_PATH);
        if (!f.exists() || f.length() == 0) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                // Confiamos en el tipo; si quieres, valida que todos sean Cliente
                return (ArrayList<Cliente>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Puedes cambiar a tu logger
        }
        return null;
    }
}
