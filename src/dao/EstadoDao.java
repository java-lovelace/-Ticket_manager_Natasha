package dao;

import domain.Estado;

public interface EstadoDao extends CRUD<Estado> {
    /**
     * Buscar estado por nombre (ej: "Abierto", "En Proceso", "Resuelto").
     */
    Estado findByNombre(String nombre);
}