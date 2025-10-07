package service;


import dao.TicketDao;
import dao.UsuarioDao;
import dao.impl.UsuarioDaoImpl;
import domain.Ticket;
import domain.Usuario;

public class TicketServiceImpl implements TicketService {
    private TicketDao ticketDao = new TicketDaoImpl();
    private UsuarioDao usuarioDao = new UsuarioDaoImpl();

    @Override
    public boolean asignarTicket(int id_ticket, int id_usuario) {

        Usuario usuario = usuarioDao.EncontrarPorId(id_usuario);

        return ticketDao.asignarTicket(id_ticket, id_usuario);
    }

    private EstadoDao estadoDao = new EstadoDaoImpl(); // si lo tienes

    @Override
    public Ticket cambiarEstado(int id_ticket) {
        Ticket ticket = ticketDao.findById(id_ticket);

        if (ticket == null) {
            System.out.println("Ticket no encontrado.");
            return null;
        }

        String estadoActual = ticket.getEstado().getNombre();
        Estado nuevoEstado = null;

        switch (estadoActual.toUpperCase()) {
            case "ABIERTO":
                nuevoEstado = estadoDao.obtenerPorNombre("EN_PROCESO");
                break;
            case "EN_PROCESO":
                nuevoEstado = estadoDao.obtenerPorNombre("CERRADO");
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

        boolean actualizado = ticketDao.actualizar(ticket);

        if (actualizado) {
            System.out.println("Estado actualizado a: " + nuevoEstado.getNombre());
            return ticket;
        } else {
            System.out.println("No se pudo actualizar el ticket.");
            return null;
        }
    }

}
