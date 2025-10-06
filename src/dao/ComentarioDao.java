package dao;
import java.util.List;
import domain.Comentario;

public interface ComentarioDao extends CRUD<Comentario>{
    /**
     * Buscar todos los comentarios asociados a un ticket.
     */
    List<Comentario> findByTicket(int idTicket);

    /**
     * Buscar todos los comentarios hechos por un usuario.
     */
    List<Comentario> findByUsuario(int idUsuario);
}
