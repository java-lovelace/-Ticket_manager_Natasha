package view;

import Controller.TicketController;

import javax.swing.*;

public class MenuReporter {

    public static void Reportes (){

        String option = "";
        String menu = """
                
                ================================
                        MENÚ TICKETS
               ================================
                    1. Crear Ticket
                    2. Salir
                ================================
                """;

        do{
            option = JOptionPane.showInputDialog(menu);
            if(option == null) break;

            switch (option){
                case "1":
                    TicketController.crecion();
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida");
            }
        }while (!option.equals("2"));

    }
}
