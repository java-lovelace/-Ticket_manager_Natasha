package service;


import dao.EstadoDao;
import dao.TicketDao;
import dao.impl.EstadoDaoImpl;
import dao.impl.TicketDaoImpl;
import domain.Estado;
import domain.Ticket;


import java.time.LocalDateTime;

public class TicketServiceImpl implements TicketService {
    private TicketDao ticketDao = new TicketDaoImpl();
    private EstadoDao estadoDao = new EstadoDaoImpl();


    @Override
    public void asignarTicket(int id_ticket, int id_usuario) {
        ticketDao.asignarTicket(id_ticket, id_usuario);
    }


    @Override
    public Ticket cambiarEstado(int id_ticket) {
        Ticket ticket = ticketDao.EncontrarPorId(id_ticket);

        if (ticket == null) {
            System.out.println("Ticket no encontrado.");
            return null;
        }

        String estadoActual = ticket.getEstado().getNombre();
        Estado nuevoEstado = null;

        switch (estadoActual.toUpperCase()) {
            case "ABIERTO":
                nuevoEstado = estadoDao.findByNombre("EN_PROCESO");
                break;
            case "EN_PROCESO":
                nuevoEstado = estadoDao.findByNombre("CERRADO");
                break;
            case "CERRADO":
                System.out.println("El ticket ya está cerrado.");
                return ticket;
            default:
                System.out.println("Estado desconocido.");
                return null;
        }

        ticket.setEstado(nuevoEstado);
        ticket.setUpdated_at(LocalDateTime.now());

        boolean actualizado = ticketDao.Actualizar(ticket);

        if (actualizado) {
            System.out.println("Estado actualizado a: " + nuevoEstado.getNombre());
            return ticket;
        } else {
            System.out.println("No se pudo actualizar el ticket.");
            return null;
        }
    }

    @Override
    public Ticket crear(Ticket ticket) {

        boolean creado = ticketDao.Crear(ticket);

        if(creado){
            return ticket;
        }else{
            System.out.println("no se completo la operacion");
            return null;
        }

    }


}
