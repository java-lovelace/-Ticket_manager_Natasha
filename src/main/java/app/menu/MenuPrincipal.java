package app.menu;

import javax.swing.*;

public class MenuPrincipal {
    public static void MainMenu(){
        String Option = "";
        String menu = """
                =====================================
                      📚   MENÚ PRINCIPAL   📚
                =====================================
                1. Asignaturas
                2. Promedio
                3. Perfil
                4. Salir
                =====================================
                👉 Selecciona una opción:
                """;

        do {
            Option = JOptionPane.showInputDialog(menu);
            if(Option == null) break;
            switch (Option){
                case "1":
                    // menu de Asignaturas
                    MenuAsignaturas.AsMenu();
                case "2":
                    // Promedio
                case "3":
                    // perfil
                case "4":
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida");
            }
        }while(!Option.equals("4"));
    }
}
