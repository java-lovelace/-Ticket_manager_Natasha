package domain;

import java.time.LocalDateTime;

public class Comentario {
    private int id_comentario;
    private Ticket ticket;
    private Usuario usuario;
    private String text;
    private LocalDateTime created_at;


    public Comentario() {}

    public Comentario(int id_comentario, LocalDateTime created_at, String text, Usuario usuario, Ticket ticket) {
        this.id_comentario = id_comentario;
        this.created_at = created_at;
        this.text = text;
        this.usuario = usuario;
        this.ticket = ticket;
    }

    public int getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Comentario" +
                "id_comentario=" + id_comentario +
                ", ticket=" + ticket +
                ", usuario=" + usuario +
                ", text='" + text + '\'' +
                ", created_at=" + created_at;
    }
}
