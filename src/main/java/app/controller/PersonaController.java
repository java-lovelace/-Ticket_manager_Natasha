package app.controller;

import app.entity.Persona;
import app.menu.MenuPrincipal;
import app.model.PersonaModel;
import app.utils.ValidacionesEntradas;

import javax.swing.JOptionPane;

public class PersonaController {
        public static void crear() {
            PersonaModel personaModel = new PersonaModel();

            String nombre = ValidacionesEntradas.LeerString("Nombre: ");
            String correo = ValidacionesEntradas.LeerCorreo("Correo: ");
            String pass =   ValidacionesEntradas.LeerContraseña("Contraseña: ");
            int edad =  ValidacionesEntradas.LeerEdad("Edad: ");


            Persona persona = new Persona();
            persona.setNombre(nombre);
            persona.setCorreo(correo);
            persona.setPass(pass);
            persona.setEdad(edad);

            persona = (Persona) personaModel.crear(persona);
            JOptionPane.showMessageDialog(null, persona.toString());

        }

        public static void login(){
            PersonaModel personaModel = new PersonaModel();
            String correo = ValidacionesEntradas.LeerCorreo("Correo: ");
            String pass = ValidacionesEntradas.LeerContraseña("Contraseña: ");

            Persona persona = personaModel.login(correo, pass);

            if (persona != null){
                JOptionPane.showMessageDialog(null, "Bienvenido usuario " + persona.getNombre());
                // aqui va el otro menu que entraria
                MenuPrincipal.MainMenu();
            }else{
                JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");

            }
        }
}
