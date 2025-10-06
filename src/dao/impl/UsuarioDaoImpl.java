package dao.impl;

import config.ConfigDB;
import dao.UsuarioDao;
import domain.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImpl implements UsuarioDao {
    @Override
    public boolean insert(Usuario obj) {
        String sql = "INSERT INTO Usuarios (nombre, correo, pass, id_rol) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConfigDB.openConnection() ;
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getCorreo());
            ps.setString(3, obj.getPass());
            ps.setInt(4, obj.getRol().getId_rol());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "✅ Usuario registrado correctamente");
            return true;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "❌ Error al registrar usuario: " + e.getMessage());
            return false;
        }

    }

    @Override
    public boolean update(Usuario obj) {
        String sql = "UPDATE Usuarios SET nombre=?, correo=?, pass=?, id_rol=? WHERE id_usuario=?";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRol());
            ps.setInt(5, u.getId());

            int Afectados = ps.executeUpdate();

            if (Afectados > 0){
                JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int obj) {
        String sql = "DELETE FROM Usuarios WHERE id_usuario=?";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            Usuario u = new Usuario();
            ps.setInt(1, u.getId_usuario());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "🗑️ Usuario eliminado correctamente");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("pass"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Usuario findById(int id) {
        Usuario u = null;
        String sql = "SELECT * FROM usuarios WHERE id=?";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("pass"),
                        rs.getString("rol")
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al buscar usuario: " + e.getMessage());
        }
        return u;
    }

    public Usuario findByEmail(String email) {
        Usuario u = null;
        String sql = "SELECT * FROM usuarios WHERE email=?";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("rol")
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al buscar usuario por email: " + e.getMessage());
        }
        return u;
    }

    @Override
    public List<Usuario> findByRol(String rol) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE rol=?";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, rol);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("rol")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al buscar por rol: " + e.getMessage());
        }
        return lista;
    }

}

