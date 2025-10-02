package espol.poo.tecnicentroapp.Modelo.TecnicosM;

public class Tecnico {
    private String identificacion;
    private String nombre;
    private String telefono;
    private String especialidad;
    //Contructor de la clase Tecnico
    public Tecnico(String identificacion, String nombre, String telefono, String especialidad) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.especialidad = especialidad;
    }
    // Getters y Setters
    public String getIdentificacion() {
        return identificacion;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Tecnico)) return false;
        Tecnico other = (Tecnico) obj;
        return this.identificacion.equals(other.identificacion); // o el campo único
    }
    @Override
    public int hashCode() {
        return identificacion.hashCode();
    }

    //metodo toString que muestra la data de un tecnico
    @Override
    public String toString() {
        return nombre;
    }
}

