package dao;

import domain.Usuario;

import java.util.List;

public class Usuarioimpl implements UsuarioDao{

    @Override
    public void crearUsuario(Usuario usuario) {

    }

    @Override
    public Usuario buscarporId(int id_usuario) {
        return null;
    }

    @Override
    public List<Usuario> ListarUsuario() {
        return List.of();
    }

    @Override
    public Usuario login(String correo, String pass) {
        return null;
    }
}
