package dao.impl;

import config.ConfigDB;
import dao.TicketDao;
import domain.Categoria;
import domain.Estado;
import domain.Ticket;
import domain.Usuario;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {
    @Override
    public void asignarTicket(int id_ticket, int idUsuario_asignado){
        String sql = "UPDATE Tickets SET assignee_id = ? WHERE id_ticket = ?";
        try(Connection conn = ConfigDB.openConnection();
            PreparedStatement prepare = conn.prepareStatement(sql)){
            prepare.setInt(1, id_ticket);
            prepare.setInt(2, idUsuario_asignado);
            prepare.executeUpdate();

            JOptionPane.showMessageDialog(null, "Ticket asignado correctamente");
        }catch (SQLException error){
            System.out.println("Error al asignar el ticket" + error.getMessage());
        }
        ConfigDB.closeConnection();
    }

    @Override
    public List<Ticket> findByAssignee(String Assignee) {
        List<Ticket> tickets = new ArrayList<>();

        String sql = """
           SELECT
            t.id_ticket,
            t.titulo,
            t.descripcion,
            t.created_at,
            t.updated_at,

            -- Datos del reportero
            r.id_usuario AS reporter_id,
            r.nombre AS reporter_nombre,
            r.correo AS reporter_correo,

            -- Datos del asignado
            a.id_usuario AS assignee_id,
            a.nombre AS assignee_nombre,
            a.correo AS assignee_correo,

            -- Datos de categoría
            c.id_categoria,
            c.nombre AS categoria_nombre,

            -- Datos de estado
            e.id_estado,
            e.nombre AS estado_nombre

        FROM Tickets t
        INNER JOIN Usuarios r ON t.reporter_id = r.id_usuario
        INNER JOIN Usuarios a ON t.assignee_id = a.id_usuario
        INNER JOIN Categorias c ON t.id_categoria = c.id_categoria
        INNER JOIN Estados e ON t.id_estado = e.id_estado
        WHERE a.nombre = ?;
    """;

        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setString(1, Assignee);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId_ticket(rs.getInt("id_ticket"));
                ticket.setTitulo(rs.getString("titulo"));
                ticket.setDescripcion(rs.getString("descripcion"));
                ticket.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                ticket.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());

                // Reporter
                Usuario reporter = new Usuario();
                reporter.setId_usuario(rs.getInt("reporter_id"));
                reporter.setNombre(rs.getString("reporter_nombre"));
                reporter.setCorreo(rs.getString("reporter_correo"));
                ticket.setReporter(reporter);

                // Asignado
                Usuario assignee = new Usuario();
                assignee.setId_usuario(rs.getInt("assignee_id"));
                assignee.setNombre(rs.getString("assignee_nombre"));
                assignee.setCorreo(rs.getString("assignee_correo"));
                ticket.setAssignee(assignee);

                // Categoría
                Categoria categoria = new Categoria();
                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("categoria_nombre"));
                ticket.setCategoria(categoria);

                // Estado
                Estado estado = new Estado();
                estado.setId_estado(rs.getInt("id_estado"));
                estado.setNombre(rs.getString("estado_nombre"));
                ticket.setEstado(estado);

                tickets.add(ticket);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error listando tickets por asignado: " + e.getMessage());
        }

        return tickets;
    }

    @Override
    public boolean Crear(Ticket obj) {
        String sql = "INSERT INTO Tickets (titulo, descripcion, reporter_id, assignee_id, id_categoria, id_estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConfigDB.openConnection() ;
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, obj.getTitulo());
            ps.setString(2, obj.getDescripcion());
            ps.setInt(3, obj.getReporter().getId_usuario());
            if(obj.getAssignee() != null){
                ps.setInt(4, obj.getAssignee().getId_usuario());
            }else{
                ps.setNull(4, Types.INTEGER);
            }
            ps.setInt(5, obj.getCategoria().getId_categoria());
            ps.setInt(6, obj.getEstado().getId_estado());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Ticket Creado exitosamente");
            return true;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al crear ticket: " + e.getMessage());
            ConfigDB.closeConnection();
            return false;
        }
    }

    @Override
    public boolean Actualizar(Ticket obj) {
        String sql = "UPDATE Tickets SET titulo = ?, descripcion = ?, reporter_id = ?, assignee_id = ?, id_categoria = ?, id_estado = ?, updated_at = CURRENT_TIMESTAMP WHERE id_ticket = ?";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, obj.getTitulo());
            ps.setString(2, obj.getDescripcion());
            ps.setInt(3, obj.getReporter().getId_usuario());

            if (obj.getAssignee() != null) {
                ps.setInt(4, obj.getAssignee().getId_usuario());
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            ps.setInt(5, obj.getCategoria().getId_categoria());
            ps.setInt(6, obj.getEstado().getId_estado());
            ps.setInt(7, obj.getId_ticket());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Ticket actualizado correctamente");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el ticket para actualizar");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el ticket: " + e.getMessage());
            return false;
        } finally {
            ConfigDB.closeConnection();
        }
    }


    @Override
    public boolean Eliminar(int obj) {
        String sql = "DELETE FROM Tickets WHERE id_ticket = ?";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, obj);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "🗑️ Ticket eliminado correctamente");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "⚠️ No se encontró el ticket con ese ID");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al eliminar el ticket: " + e.getMessage());
            return false;
        } finally {
            ConfigDB.closeConnection();
        }
    }


    @Override
    public List<Ticket> Todos() {
        List<Ticket> list = new ArrayList<>();
        String sql = "SELECT * FROM Tickets";

        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId_ticket(rs.getInt("id_ticket"));
                ticket.setTitulo(rs.getString("titulo"));
                ticket.setDescripcion(rs.getString("descripcion"));
                ticket.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                ticket.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());

                Usuario reporter = new Usuario();
                reporter.setId_usuario(rs.getInt("reporter_id"));
                ticket.setReporter(reporter);

                Usuario assignee = new Usuario();
                assignee.setId_usuario(rs.getInt("assignee_id"));
                ticket.setAssignee(assignee);

                Categoria categoria = new Categoria();
                categoria.setId_categoria(rs.getInt("id_categoria"));
                ticket.setCategoria(categoria);

                Estado estado = new Estado();
                estado.setId_estado(rs.getInt("id_estado"));
                ticket.setEstado(estado);

                list.add(ticket);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al listar los tickets: " + e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return list;
    }



    @Override
    public Ticket EncontrarPorId(int id) {
        Ticket ticket = null;
        String sql = "SELECT * FROM Tickets WHERE id_ticket = ?";
        try(Connection conn = ConfigDB.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                ticket = new Ticket();
                ticket.setId_ticket(rs.getInt("id_ticket"));
                ticket.setTitulo(rs.getString("titulo"));
                ticket.setDescripcion(rs.getString("descripcion"));
                ticket.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                ticket.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());

                Usuario reporter = new Usuario();
                reporter.setId_usuario(rs.getInt("reporter_id"));
                ticket.setReporter(reporter);

                Usuario assignee = new Usuario();
                assignee.setId_usuario(rs.getInt("assignee_id"));
                ticket.setAssignee(assignee);

                Categoria categoria = new Categoria();
                categoria.setId_categoria(rs.getInt("id_categoria"));
                ticket.setCategoria(categoria);

                Estado estado = new Estado();
                estado.setId_estado(rs.getInt("id_estado"));
                ticket.setEstado(estado);

            }

        }catch (SQLException error){
            System.out.println("Error al encontrar el ticket" + error.getMessage());
        }

        ConfigDB.closeConnection();
        return ticket;
    }

}