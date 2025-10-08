package Controller;

import dao.UsuarioDao;
import dao.impl.UsuarioDaoImpl;
import domain.Rol;
import domain.Usuario;
import service.UsuarioServiceImpl;
import view.MenuReporter;

import javax.swing.*;

public class LogOut {

    public static void Login(){
        String correo = JOptionPane.showInputDialog("Correo: ");
        String pass = JOptionPane.showInputDialog("Contraseña: ");

        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(usuarioDao);
        Usuario usuario = usuarioService.login(correo, pass);

        if(usuario != null){
            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombre());
            int rolNombre = usuario.getRol().getId_rol();
            switch (rolNombre){
                case 1:
                    MenuReporter.Reportes();
                    break;
                case 2:
                    break;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
        }
    }

    public static void Register (){
        String nombre = JOptionPane.showInputDialog("Nombre: ");
        String correo = JOptionPane.showInputDialog("Correo: ");
        String pass = JOptionPane.showInputDialog("Contraseña: ");

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setPass(pass);

        Rol rol = new Rol();
        rol.setId_rol(1);
        usuario.setRol(rol);


        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(usuarioDao);

        boolean registrado = usuarioService.registrar(usuario);
        if (registrado){
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario");
        }



    }



}
