package dao.impl;

import config.ConfigDB;
import dao.EstadoDao;
import domain.Estado;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EstadoDaoImpl implements EstadoDao {
    @Override
    public boolean Crear(Estado obj) {
        return false;
    }

    @Override
    public boolean Actualizar(Estado obj) {
        String sql = "UPDATE Estados SET nombre = ?";
        try(Connection conn = ConfigDB.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){

            ps.setString(1, obj.getNombre());


            int rowsAfectadas =ps.executeUpdate();

            if(rowsAfectadas > 0){
                JOptionPane.showMessageDialog(null, "Estado actualizado");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "No existe");
            }
        }catch (SQLException error){
            System.out.println("Error al actualizar" + error.getMessage());
        }
        return false;
    }

    @Override
    public boolean Eliminar(int obj) {
        return false;
    }

    @Override
    public List<Estado> Todos() {
        return List.of();
    }

    @Override
    public Estado EncontrarPorId(int id) {
        return null;
    }
}
