package view;

import javax.swing.*;

public class MenuReporter {

    public static void Reportes (){

        String option = "";
        String menu = """
                
                ================================
                        MENÚ TICKETS
               ================================
                    1. Crear Ticket
                    2. Asignar Ticket
                    3. Salir
                ================================
                """;

        do{
            option = JOptionPane.showInputDialog(menu);
            if(option == null) break;

            switch (option){
                case "1":

            }
        }while (!option.equals("3"));

    }
}
