package dao;
import java.util.List;

public interface CRUD<T> {
    boolean Crear (T obj);
    boolean Actualizar (T obj);
    boolean Eliminar (int obj);
    List<T> Todos();
    T EncontrarPorId(int id);

}
