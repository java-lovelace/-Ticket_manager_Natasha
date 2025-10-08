package service;

import domain.Ticket;

public interface TicketService {

    public void asignarTicket(int id_ticket, int id_usuario);

    public Ticket cambiarEstado(int id_ticket) ;

    Ticket crear (Ticket ticket);

    void asignar (int id_ticket, int assignee_id);
}
