package app.utils;

import javax.swing.*;

public class ValidacionesEntradas {


    public static String LeerString (String mensaje){
        while(true){
            String entrada = JOptionPane.showInputDialog(null, mensaje);
            if(entrada == null){
                JOptionPane.showMessageDialog(null,"Se cancelo");
                return null;
            }
            entrada = entrada.trim();
            if (entrada.isEmpty()){
                JOptionPane.showMessageDialog(null, "No puede estar vacio");
                continue;
            }
            try{
                Double.parseDouble(entrada);
                JOptionPane.showMessageDialog(null, "No peudes poner un numero");
                continue;
            }catch (NumberFormatException error){
                return entrada;
            }
        }
    }
    public static String LeerCorreo(String mensaje) {
        while(true){
            String correo = JOptionPane.showInputDialog(null, "Correo: ");
            if (correo == null){
                JOptionPane.showMessageDialog(null, "Se cancelo");
                return null;
            }
            correo = correo.trim();
            if(!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|io|net|org)$")){
                JOptionPane.showMessageDialog(null, "Correo invalido");
                continue;
            }
            return correo;
        }
    }

    public static String LeerStringconNumero(String mensaje){
        while (true){
            String entrada = JOptionPane.showInputDialog(null, mensaje);
            if (entrada == null){
                return null;
            }
            entrada = entrada.trim();
            if (entrada.isEmpty()){
                JOptionPane.showMessageDialog(null, "No puede estar vacio");
                continue;
            }
            return entrada;
        }
    }

    public static String LeerContraseña(String mensaje){
        while(true){
            String pass = JOptionPane.showInputDialog(null, mensaje);
            if (pass == null){
                JOptionPane.showMessageDialog(null, "Se cerro");
                return null;
            }
            pass = pass.trim();
            if(pass.length()< 6){
                JOptionPane.showMessageDialog(null, "La contraseña debe tener 6 caracteres");
                continue;
            }
            return pass;
        }
    }
    public static int LeerEdad(String mensaje){
      while(true){
          String edad = JOptionPane.showInputDialog(null, mensaje);
          if (edad == null){
              JOptionPane.showMessageDialog(null, "Se cancelo");
              return -1; // Esto indica cancelación
          }
          try{
              int numero = Integer.parseInt(edad);
              if(numero <= 0){
                  JOptionPane.showMessageDialog(null, "Ingresa un numero valido");
                  continue;
              }
              return numero;
          }catch (NumberFormatException error){
              JOptionPane.showMessageDialog(null, "El caracter debe se un numero");
          }

      }
    }
}
