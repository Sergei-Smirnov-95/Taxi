package Repository;

import javax.management.Query;
import java.util.List;

public interface AbstractRepository<T> {
    List<T> all() throws DatabaseException;
    T get(Query query) throws DatabaseException;
    void create(T item) throws DatabaseException;
    void update(T item) throws DatabaseException;
    boolean remove(Query query) throws DatabaseException;
    boolean remove(T item) throws DatabaseException;
    List<T> filter(Query query) throws DatabaseException;
}
