package dao.impl;

import config.ConfigDB;
import dao.UsuarioDao;
import domain.Rol;
import domain.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UsuarioDaoImpl implements UsuarioDao {
    @Override
    public boolean Crear(Usuario obj) {
        String sql = "INSERT INTO Usuarios (nombre, correo, pass, id_rol) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConfigDB.openConnection() ;
            PreparedStatement ps = conn.prepareStatement(sql)){

            obj.getRol().setId_rol(1);
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getCorreo());
            ps.setString(3, obj.getPass());
            ps.setInt(4, obj.getRol().getId_rol());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("error" + e.getMessage());
            return false;
        }

    }

    @Override
    public boolean Actualizar(Usuario obj) {
        return false;
    }



    @Override
    public boolean Eliminar(int obj) {
        return false;
    }


    //Necesitamos esta parte
    @Override
    public List<Usuario> Todos() {
        List<Usuario> Lista = new ArrayList<>();

        return Lista;
    }
    // Encontrar por Id necesario
    @Override
    public Usuario EncontrarPorId(int id) {
        return null;
    }


    @Override
    public List<Rol> EncontrarRol(String nombre) {
        return List.of();
    }

    @Override
    public HashMap<Usuario, Rol> listadeUsuariosconRol() {
        HashMap<Usuario, Rol> usuariosAssignee = new HashMap<>();

        String sql = """
                SELECT u.id_usuario, u.nombre, u.correo, u.pass,
                r.id_rol, r.nombre AS rol_nombre
                FROM Usuarios u
                INNER JOIN Roles r ON u.id_rol = r.id_rol
                WHERE r.id_rol = 2
                """;

        try(Connection conn = ConfigDB.openConnection();
            PreparedStatement prepare = conn.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery()){
            while(rs.next()){

                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPass(rs.getString("pass"));

                Rol rol = new Rol();
                rol.setId_rol(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("rol_nombre"));


                usuariosAssignee.put(usuario, rol);
            }
        }catch (SQLException error){
            System.out.println("Error" + error);
        }
        return usuariosAssignee;
    }


    @Override
    public Usuario EncontrarEmail(String correo) {
        Usuario usuario = null;
        String sql =    "SELECT u.*, r.id_rol AS rol_id, r.nombre AS rol_nombre " +
                        "FROM Usuarios u " +
                        "JOIN Roles r ON u.id_rol = r.id_rol " +
                        "WHERE u.correo = ?";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement prepare = conn.prepareStatement(sql)) {
            prepare.setString(1, correo);
            ResultSet rs = prepare.executeQuery();


            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPass(rs.getString("pass"));

                Rol rol = new Rol();
                rol.setId_rol(rs.getInt("rol_id"));
                rol.setNombre(rs.getString("rol_nombre"));

                usuario.setRol(rol);
            }
        } catch (SQLException error) {
            System.out.println("error" + error.getMessage());
        }
        return usuario;
    }
}

