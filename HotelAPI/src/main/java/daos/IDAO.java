package daos;

import java.util.List;

public interface IDAO<T>{


    List<T> getAll();


    T getById(int id);


    void create(T entity);


    void update(T entity);


    void delete(int id);
}

