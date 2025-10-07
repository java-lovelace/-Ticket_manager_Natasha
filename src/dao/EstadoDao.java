package dao;

import domain.Estado;

public interface EstadoDao extends CRUD<Estado> {

    Estado findByNombre(String nombre);
}