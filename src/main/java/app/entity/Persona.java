package app.entity;

public class Persona {
    private int id_persona;
    private String nombre;
    private String correo;
    private String pass;
    private int edad;

public Persona(){}

    public Persona(int id_persona, String nombre, String correo, String pass, int edad) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
        this.edad = edad;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id_persona=" + id_persona +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", pass='" + pass + '\'' +
                ", edad=" + edad +
                '}';
    }
}
