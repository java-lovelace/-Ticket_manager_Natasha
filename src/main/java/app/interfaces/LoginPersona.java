package app.interfaces;

import app.entity.Persona;

public interface LoginPersona extends CRUD<Persona>{
    Persona login(String correo, String pass);
}
