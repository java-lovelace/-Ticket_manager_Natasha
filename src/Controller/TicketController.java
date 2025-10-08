package Controller;

import dao.UsuarioDao;
import dao.impl.UsuarioDaoImpl;
import domain.Categoria;
import domain.Estado;
import domain.Ticket;
import domain.Usuario;
import service.TicketService;
import service.TicketServiceImpl;
import view.LoginRegister;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TicketController {

    private static TicketService ticketService = new TicketServiceImpl();
    public static void crecion () {
        String titulo = JOptionPane.showInputDialog("Titulo: ");
        String descripcion = JOptionPane.showInputDialog("Descripcion: ");

        String[] Categorias = {"Infraestructura", "Aplicación", "Cuenta"};
        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona la categoria",
                "Categoria",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Categorias,
                Categorias[0]
        );

        int idCategoria = switch (seleccion){
            case "Infraestructura" -> 1;
            case "Aplicación" -> 2;
            case "Cuenta" -> 3;
            default -> 1;
        };

        Categoria categoria = new Categoria();
        categoria.setId_categoria(idCategoria);
        categoria.setNombre(seleccion);


        Estado estado = new Estado();
        estado.setId_estado(1);
        estado.setNombre("Abierto");

        Usuario reporter = LogOut.usuarioLogueado;

        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        HashMap<Usuario, ?> mapaUsuarios = usuarioDao.listadeUsuariosconRol();

        List<Usuario> listaUsuarios = new ArrayList<>(mapaUsuarios.keySet()); // Para convertir en Lista el HashMap

        Random random = new Random();
        Usuario assignee = listaUsuarios.get(random.nextInt(listaUsuarios.size()));

        Ticket ticket = new Ticket();
        ticket.setTitulo(titulo);
        ticket.setDescripcion(descripcion);
        ticket.setReporter(LogOut.usuarioLogueado);
        ticket.setCategoria(categoria);
        ticket.setEstado(estado);
        ticket.setAssignee(assignee);


        ticketService.crear(ticket);
        JOptionPane.showMessageDialog(null, "ticket creado exitosamente y asignado a: " + assignee.getNombre());
    }
}
