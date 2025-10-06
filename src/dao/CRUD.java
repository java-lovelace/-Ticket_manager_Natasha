package dao;
import java.util.List;

public interface CRUD<T> {
    boolean insert (T obj);
    boolean update (T obj);
    boolean delete (int obj);
    List<T> findAll();
    T findById(int id);

}
