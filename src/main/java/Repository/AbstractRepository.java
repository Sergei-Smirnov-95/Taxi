package Repository;

import javax.management.Query;
import java.sql.SQLException;
import java.util.List;

public interface AbstractRepository<T> {

    List<T> Getall() throws DatabaseException;
    T GetById(int id) throws  DatabaseException,SQLException;
    //T get(Query query) throws DatabaseException;
    void add(T item) throws DatabaseException;
    /*void update(List<T> lst) throws DatabaseException;
    boolean remove(Query query) throws DatabaseException;
    boolean remove(T item) throws DatabaseException;
    List<T> filter(Query query) throws DatabaseException;*/
}
