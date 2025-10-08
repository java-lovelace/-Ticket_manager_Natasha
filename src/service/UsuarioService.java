package service;

import domain.Usuario;

public interface UsuarioService {
    Usuario login (String email, String pass);
    boolean registrar (Usuario usuario);

}
