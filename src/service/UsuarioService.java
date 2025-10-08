package service;

import dao.UsuarioDao;
import domain.Rol;
import domain.Usuario;

import java.util.HashMap;

public interface UsuarioService {
    Usuario login (String email, String pass);
    boolean registrar (Usuario usuario);
    HashMap<Usuario, Rol> listaDeUsuariosAssinee ();
}
