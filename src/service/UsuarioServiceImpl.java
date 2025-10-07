package service;


import dao.UsuarioDao;
import domain.Usuario;

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioDao usuarioDao;

    public UsuarioServiceImpl(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }


    @Override
    public Usuario login(String correo, String password) {
        Usuario usuario = usuarioDao.EncontrarEmail(correo);

        if (usuario != null) {
            // Comparar contraseña (idealmente encriptada)
            if (usuario.getPass().equals(password)) {
                return usuario;
            }
        }
        return null;
    }

}
