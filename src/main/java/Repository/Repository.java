package Repository;

import Exceptions.DatabaseException;

import javax.management.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

abstract public class Repository<T> implements AbstractRepository<T> {

    private List<T> list;//= new ArrayList();

    @Override
    public  List<T> getall() throws SQLException {
           //System.out.println("list of alls");
        return list;
    }
    @Override
    public T getById(int id) throws  SQLException{return null;}/*{
        for (T item:list) {
            if(item.Getid() == id){
            }
        }

    }*/
    /*@Override
    public T get(Query query) throws DatabaseException {
        return null;
    }
    */
    public void create(T item) throws DatabaseException {
        list = new ArrayList();
        list.add(item);
    }
    @Override
    public void add(T item) throws DatabaseException {
        list.add(item);
    }

    /*@Override
    public void update(List<T> lst) throws DatabaseException {
        /*for (T item:lst ) {

        }*/
    /*}

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
    }*/
}
