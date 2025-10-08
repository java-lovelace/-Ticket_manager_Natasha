package service;


import dao.UsuarioDao;
import dao.impl.UsuarioDaoImpl;
import domain.Rol;
import domain.Usuario;

import javax.swing.*;
        import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Override
    public boolean registrar(Usuario usuario) {

        if(usuario.getCorreo() == null || usuario.getPass() == null || usuario.getNombre() == null){
            JOptionPane.showMessageDialog(null, "Datos incompletos en el registro");
            return false;
        }

        Usuario existente = usuarioDao.EncontrarEmail(usuario.getCorreo());
        if (existente != null){
            JOptionPane.showMessageDialog(null, "El usuario ya existe");
            return false;
        }
        return usuarioDao.Crear(usuario);
    }

    @Override
    public HashMap<Usuario, Rol> listaDeUsuariosAssinee() {
        return usuarioDao.listadeUsuariosconRol();
    }
}
