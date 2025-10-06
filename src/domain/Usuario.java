package domain;

public class Usuario {
    private int id_usuario;
    private String nombre;
    private String correo;
    private String pass;
    private Rol rol;

    public Usuario() {}

    public Usuario(int id_usuario, String nombre, String correo, Rol rol, String pass) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.pass = pass;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", pass='" + pass + '\'' +
                ", rol=" + rol;
    }
}
