package view;

import Controller.LogOut;
import Controller.TicketController;

import javax.swing.*;

public class MenuAssignee {
    public static void menu(){
        String option = "";
        String menu = """
                    1. Buscar estado + Categoria
                    2. Listar por asignado
                    3. Top 3 categorias
                    4. Salir
                """;
        do {
            option = JOptionPane.showInputDialog(menu);
            if (option == null) break;

            switch (option){
                case "1"-> TicketController.EncontrarCategoriaEstado();
                case "2" -> TicketController.mostrarlistado();
                case "3" -> TicketController.mostrarTop();
                case "4"-> JOptionPane.showMessageDialog(null, "Saliendo del sistema");
                default -> JOptionPane.showMessageDialog(null, "Opcion no valida");
            }
        }while (!option.equals("4"));
    }
}
