package service;


public class TicketServiceImpl implements TicketService {
    private TicketDAO ticketDAO = new TicketDAOImpl();
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @Override
    public boolean asignarTicket(int idTicket, int idUsuario) {
        // ✅ Aquí van las reglas de negocio, no SQL
        Usuario usuario = usuarioDAO.buscarPorId(idUsuario);

        if (usuario == null) {
            System.out.println("El usuario no existe.");
            return false;
        }

        return ticketDAO.asignarTicket(idTicket, idUsuario);
    }
}
