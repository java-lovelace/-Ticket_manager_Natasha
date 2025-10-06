package app.entity;

import java.math.BigDecimal;

public class NotasAsignatura {
    private int  id_notasAsignatura;
    private int id_persona;
    private int id_asignatura;
    private BigDecimal notas;

    public NotasAsignatura() {}

    public NotasAsignatura(int id_notasAsignatura, int id_persona, int id_asignatura, BigDecimal notas) {
        this.id_notasAsignatura = id_notasAsignatura;
        this.id_persona = id_persona;
        this.id_asignatura = id_asignatura;
        this.notas = notas;
    }

    public int getId_notasAsignatura() {
        return id_notasAsignatura;
    }

    public void setId_notasAsignatura(int id_notasAsignatura) {
        this.id_notasAsignatura = id_notasAsignatura;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public BigDecimal getNotas() {
        return notas;
    }

    public void setNotas(BigDecimal notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "NotasAsignatura{" +
                "id_notasAsignatura=" + id_notasAsignatura +
                ", id_persona=" + id_persona +
                ", id_asignatura=" + id_asignatura +
                ", notas=" + notas +
                '}';
    }
}
