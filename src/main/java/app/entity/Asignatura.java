package app.entity;

public class Asignatura {
    private int id_asignatura;
    private String nombreAsignatura;

    public Asignatura() {}

    public Asignatura(int id_asignatura, String nombreAsignatura) {
        this.id_asignatura = id_asignatura;
        this.nombreAsignatura = nombreAsignatura;
    }

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "id_asignatura=" + id_asignatura +
                ", nombreAsignatura='" + nombreAsignatura + '\'' +
                '}';
    }
}
