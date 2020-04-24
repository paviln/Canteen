package persistence;

import java.util.List;
import java.util.Optional;

public interface Dao<T>
{
    Object get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
