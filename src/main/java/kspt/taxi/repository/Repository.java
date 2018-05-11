package kspt.taxi.repository;

import kspt.taxi.exceptions.DatabaseException;
import sun.reflect.generics.repository.AbstractRepository;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T> {

    protected List<T> list = new ArrayList();

    public List<T> getAll() throws DatabaseException {
        return null;
    }

    public T get(Query query) throws DatabaseException {
        return null;
    }

    public void create(T item) throws DatabaseException {

    }

    public void update(T item) throws DatabaseException {

    }

    public List<T> filter(Query query) throws DatabaseException {
        return null;
    }
}
