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
    public boolean asignarTicket(int idTicket, int idUsuario) {
        // ✅ Aquí van las reglas de negocio, no SQL
        Usuario usuario = usuarioDao.buscarPorId(idUsuario);

        if (usuario == null) {
            System.out.println("El usuario no existe.");
            return false;
        }

        return ticketDao.asignarTicket(idTicket, idUsuario);
    }

    @Override
    public Ticket cambiarEstado(int id_ticket) {
        Ticket ticket = ticketDao.findById(id_ticket);
    }
}
