package espol.poo.tecnicentroapp.Modelo;

public abstract class Usuario {
    protected String identificacion;
    protected String nombre;
    protected String telefono;
    //Constructor de la clase abstracta Usuario
    public Usuario(String identificacion, String nombre, String telefono) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    //Getters and setters
    public String getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}