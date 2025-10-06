package dao;

import domain.Ticket;

import java.util.List;

public interface TicketDao extends CRUD<Ticket>{
    /**
     * Buscar tickets asignados a un usuario (assignee).
     * Debe incluir datos combinados del reporter, categoría y estado (JOIN completo).
     */
    List<Ticket> findByAssignee(int idAssignee);

    /**
     * Buscar tickets filtrados por estado y categoría (JOIN filtrado).
     */
    List<Ticket> findByEstadoAndCategoria(int idEstado, int idCategoria);

    /**
     * Obtener el top 3 de categorías con más tickets (agregación con GROUP BY).
     *
     * @return Lista de arreglos Object[]: [nombreCategoria, cantidadTickets]
     */
    List<Object[]> findTopCategorias();

}
