package dao.impl;

import config.ConfigDB;
import dao.TicketDao;
import domain.Ticket;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
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
            return false;
        }
    }

    @Override
    public boolean Actualizar(Ticket obj) {
        return false;
    }

    @Override
    public boolean Eliminar(int obj) {
        return false;
    }

    @Override
    public List<Ticket> Todos() {
        return List.of();
    }

    @Override
    public Ticket EncontrarPorId(int id) {
        return null;
    }

    }
