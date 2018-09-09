package Repository;

import Exceptions.DatabaseException;

import javax.management.Query;
import java.sql.SQLException;
import java.util.List;

public interface AbstractRepository<T> {

    List<T> getall() throws SQLException;
    T getById(int id) throws  SQLException;
    //T get(Query query) throws DatabaseException;
    void add(T item) throws DatabaseException;
    /*void update(List<T> lst) throws DatabaseException;
    boolean remove(Query query) throws DatabaseException;
    boolean remove(T item) throws DatabaseException;
    List<T> filter(Query query) throws DatabaseException;*/
}
