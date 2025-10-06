package dao;

import domain.Usuario;

import java.util.List;

public interface UsuarioDao extends CRUD<Usuario>{
    List<Usuario> findByRol(String rol);
}