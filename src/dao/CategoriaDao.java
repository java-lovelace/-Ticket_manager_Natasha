package dao;
import domain.Categoria;
import java.util.List;

public interface CategoriaDao extends CRUD<Categoria>{
    /**
     * Buscar una categoría por nombre.
     */
    Categoria findByNombre(String nombre);

    /**
     * Obtener todas las categorías con la cantidad de tickets asociados (JOIN).
     */
    List<Object[]> findCategoriasConConteo();
}
