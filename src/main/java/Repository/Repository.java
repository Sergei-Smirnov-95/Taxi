package Repository;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

public class Repository<T> implements AbstractRepository<T> {

    protected List<T> list = new ArrayList();

    @Override
    public List<T> all() throws DatabaseException {
        return null;
    }

    @Override
    public T get(Query query) throws DatabaseException {
        return null;
    }

    @Override
    public void create(T item) throws DatabaseException {

    }

    @Override
    public void update(T item) throws DatabaseException {

    }

    @Override
    public boolean remove(Query query) throws DatabaseException {
        return false;
    }

    @Override
    public boolean remove(T item) throws DatabaseException {
        return false;
    }

    @Override
    public List<T> filter(Query query) throws DatabaseException {
        return null;
    }
}
