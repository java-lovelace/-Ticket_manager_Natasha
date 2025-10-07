package view;

import Controller.LogOut;

import javax.swing.*;

public class LoginRegister {
    public static void LogOut() {
        String option = "";
        String menu = """
                ===========================
                        LOGIN SYSTEM
                ===========================
                1. Iniciar Sesión
                2. Registrarse
                3. Salir
                ===========================
                """;
        do {
            option = JOptionPane.showInputDialog(menu);
            if (option == null) break;

            switch (option){
                case "1"-> LogOut.Login();
                case "2" -> JOptionPane.showMessageDialog(null, "s");
                case "3"-> JOptionPane.showMessageDialog(null, "Saliendo del sistema");
                default -> JOptionPane.showMessageDialog(null, "Opcion no valida");
            }
        }while (!option.equals("3"));
    }
}
