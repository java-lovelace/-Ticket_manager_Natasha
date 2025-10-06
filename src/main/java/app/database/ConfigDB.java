package app.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    public static Connection Conexion = null;


    public static Connection openConnection () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://mysql-cb47f1b-mazoosebas-b7e8.g.aivencloud.com:26554/usersp?sslMode=REQUIRED";
            String user = "avnadmin";
            String password = "AVNS_2Qftsx_8VJDBC7AkaRx";

            Conexion = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexion a la base de datos exitosa");
        } catch (ClassNotFoundException error) {
            System.out.println("Driver no instalado " + error);
        } catch (SQLException error) {
            System.out.println("Error al conectar con la base de datos " + error);
        }
    return Conexion;
    }
    public static void closeConnection () {
        try {
            if(Conexion != null){
                Conexion.close();
                System.out.println("Se ha finalizado la conexion");
            }
        }catch (SQLException error){
            System.out.println("Error" + error.getMessage());
        }
    }
}

