package app.model;

import app.entity.Asignatura;
import app.interfaces.CRUD;

import java.util.List;

public class AsignaturaModel implements CRUD<Asignatura>{
    @Override
    public Asignatura crear(Asignatura objeto) {
        return null;
    }

    @Override
    public List<Asignatura> listar() {
        return List.of();
    }

    @Override
    public Asignatura leerporId(int id) {
        return null;
    }

    @Override
    public Asignatura actualizar(Asignatura objeto) {
        return null;
    }

    @Override
    public boolean eliminar(int id) {
        return false;
    }
}
