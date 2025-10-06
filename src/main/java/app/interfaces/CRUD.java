package app.interfaces;

import java.util.List;

public interface CRUD<T> {
    public T crear(T objeto);
    public List<T> listar();
    public T leerporId(int id);
    public T actualizar (T objeto);
    public boolean eliminar(int id);

}
