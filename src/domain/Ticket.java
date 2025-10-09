package domain;

import java.time.LocalDateTime;

public class Ticket {
    private int id_ticket;
    private String titulo;
    private String descripcion;
    private Usuario reporter;
    private Usuario assignee;
    private Categoria categoria;
    private Estado estado;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Ticket() {}

    public Ticket(int id_ticket, LocalDateTime updated_at, LocalDateTime created_at, String titulo, String descripcion, Usuario reporter, Usuario assignee, Categoria categoria, Estado estado) {
        this.id_ticket = id_ticket;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.reporter = reporter;
        this.assignee = assignee;
        this.categoria = categoria;
        this.estado = estado;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getReporter() {
        return reporter;
    }

    public void setReporter(Usuario reporter) {
        this.reporter = reporter;
    }

    public Usuario getAssignee() {
        return assignee;
    }

    public void setAssignee(Usuario assignee) {
        this.assignee = assignee;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Ticket" +
                "id_ticket=" + id_ticket +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", reporter=" + reporter +
                ", assignee=" + assignee +
                ", categoria=" + categoria +
                ", estado=" + estado +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at;
    }

    public String mostrarInfo() {
        return "📄 Ticket: " +
                "\n🧍‍♀️ Reporter: " + reporter.getNombre() +
                "\n👨‍💻 Asignado a: " + assignee.getNombre() +
                "\n🏷️ Categoría: " + categoria.getNombre() +
                "\n📌 Estado: " + estado.getNombre() +
                "\n------------------------------\n";
    }
}
