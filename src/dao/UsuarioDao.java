package dao;

import domain.Usuario;
import java.util.List;

public interface UsuarioDao {
    void crearUsuario(Usuario usuario);
    Usuario buscarporId(int id_usuario);
    List<Usuario> ListarUsuario();
    Usuario login (String correo, String pass);
}