package service;

import domain.Ticket;

public interface TicketService {

    public boolean asignarTicket(int id_ticket, int id_usuario);

    public Ticket cambiarEstado(int id_ticket) ;
}
