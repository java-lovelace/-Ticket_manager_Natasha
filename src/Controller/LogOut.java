package Controller;

import dao.UsuarioDao;
import dao.impl.UsuarioDaoImpl;
import domain.Usuario;
import service.UsuarioService;
import service.UsuarioServiceImpl;

import javax.swing.*;

public class LoginRegister {

    public static void Login(){
        String correo = JOptionPane.showInputDialog("Correo: ");
        String pass = JOptionPane.showInputDialog("Contraseña: ");

        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(usuarioDao);
        Usuario usuario = usuarioService.login(correo, pass);

        if(usuario != null){
            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombre());
        }else{
            JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
        }
    }



}
