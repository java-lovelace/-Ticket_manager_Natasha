package dao;

import domain.Ticket;

import java.util.List;

public interface TicketDao extends CRUD<Ticket>{
    void asignarTicket(int id_ticket, int idUsuario_asignado);
    List<Ticket> findByAssignee(String Assignee);
    List<Ticket> estadoCategoria (String nombreEstado, String nombreCategoria);
    List<Ticket> listadoTickets ();
    List<String> topCategorias ();
}