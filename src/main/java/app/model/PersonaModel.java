package app.model;

import app.database.ConfigDB;
import app.entity.Persona;
import app.interfaces.CRUD;
import app.interfaces.LoginPersona;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class PersonaModel implements LoginPersona{

// Se usa en menu Login register
    @Override
    public Persona crear(Persona objeto) {

        Persona persona = (Persona) objeto;
        String sql = "INSERT INTO Persona(nombre, correo, pass, edad) VALUES (?,?,?,?)";
        try (Connection connexion = ConfigDB.openConnection();
             PreparedStatement prepare = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){

            prepare.setString(1, persona.getNombre());
            prepare.setString(2, persona.getCorreo());
            prepare.setString(3, persona.getPass());
            prepare.setInt(4, persona.getEdad());

            prepare.executeUpdate();

            try(ResultSet resultado = prepare.getGeneratedKeys();){
                if(resultado.next()){
                    persona.setId_persona(resultado.getInt(1));
                }
            }
            JOptionPane.showMessageDialog(null, "Usuario Registrado exitosamente");

        }catch (SQLException error){
            System.out.println("Error" + error);
        }
        ConfigDB.closeConnection();
        return persona;
    }
// No se usa pero se tiene por que si se elimina genera error
    @Override
    public List<Persona> listar() {
        return List.of();
    }
//No se usa porque ya tenemos metodo Login
    @Override
    public Persona leerporId(int id) {
        return null;
    }
// se usa en el menu de perfil
    @Override
    public Persona actualizar(Persona objeto) {
        return null;
    }
// No se usa por el momento ya que esta funcion solo se le delega al rol admin y de momento no hay
    @Override
    public boolean eliminar(int id) {
        return false;
    }
// se usa para logiarnos en el sistema
    @Override
    public Persona login(String correo, String pass) {
        Persona persona = null;

        String sql = "SELECT * FROM Persona WHERE correo = ? AND pass = ?";
        try(Connection connexion = ConfigDB.openConnection();
        PreparedStatement prepare = connexion.prepareStatement(sql);){

            prepare.setString(1, correo);
            prepare.setString(2, pass);

            try( ResultSet resultado = prepare.executeQuery()){
                if(resultado.next()){
                    persona = new Persona();
                    persona.setId_persona(resultado.getInt("id_persona"));
                    persona.setNombre(resultado.getString("nombre"));
                    persona.setCorreo(resultado.getString("correo"));
                    persona.setPass(resultado.getString("pass"));
                    persona.setEdad(resultado.getInt("edad"));
                }
            }
        }catch (SQLException error){
            System.out.println("Error" + error.getMessage());
        }
        return persona;
    }
}
