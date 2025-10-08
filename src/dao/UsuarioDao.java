package dao;

import domain.Rol;
import domain.Usuario;

import java.util.HashMap;
import java.util.List;

public interface UsuarioDao extends CRUD<Usuario>{
    List<Rol> EncontrarRol(String nombre);
    HashMap<Usuario, Rol> listadeUsuariosconRol();
    Usuario EncontrarEmail(String correo);
}