package DataSource;

import BusinessLogic.Driver;
import BusinessLogic.Operator;
import BusinessLogic.Order;
import BusinessLogic.Passenger;
import DataSource.DBRealisation.DriverDatabase;
import DataSource.DBRealisation.OperatorDatabase;
import DataSource.DBRealisation.OrderDatabase;
import DataSource.DBRealisation.PassengerDatabase;
import Exceptions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Repository  {

    private  static Repository instance;

    private static DriverDatabase driverDatabase ;
    private static OperatorDatabase operatorDatabase ;
    private static OrderDatabase orderDatabase ;
    private static PassengerDatabase passengerDatabase ;

    private static List<Operator> oplist = new ArrayList<>();
    private static List<Driver> drlist= new ArrayList<>();
    private static List<Passenger> paslist= new ArrayList<>();
    private static List<Order> orlist= new ArrayList<>();



    public static Repository getInstance() throws DBConnectionException,DBAccessException {
        if(instance == null){
            synchronized (Repository.class) {
                if(instance == null){
                    instance = new Repository();
                }
            }
        }
        return instance;
    }

    private Repository() throws DBConnectionException,DBAccessException {
        try {
            if (driverDatabase == null) driverDatabase = new DriverDatabase();
            drlist = driverDatabase.getall();
            if (operatorDatabase == null) operatorDatabase = new OperatorDatabase();
            oplist = operatorDatabase.getall();
            if (orderDatabase == null) orderDatabase = new OrderDatabase();
            orlist = orderDatabase.getAllOrders();
            if (passengerDatabase == null) passengerDatabase = new PassengerDatabase();
            paslist = passengerDatabase.getall();

        } catch (SQLException e) {
            throw new DBConnectionException();
        } catch ( IllegalAccessException e){
            throw new DBAccessException();
        }
    }

    public Operator getOperatorById(int id) throws DBConnectionException{
        if(!oplist.isEmpty() ){
            for (Operator item : oplist) {
                if (item.getId() == id) {
                    return item;
                }
            }
        }
        try{
            return operatorDatabase.getById(id);
        }
        catch (SQLException ex){
            throw new DBConnectionException();
        }

    }
    public void addOperator(Operator item) throws DBConnectionException{

        try{
            int id = operatorDatabase.add(item);
            if(id != -1)
            {
                item.setId(id);
                oplist.add(item);
            }
            else
            {
                throw new DBConnectionException();
            }
        }
        catch (SQLException ex){
            throw new DBConnectionException();
        }

    }

    public Operator getOperatorByLogin(String login) throws DBConnectionException, HaveNotUserEx{
        if(!oplist.isEmpty() ){
            for (Operator item : oplist) {
                if (item.getLogin().equals(login)) {
                    return item;
                }
            }
        }
        try{
            Operator op = operatorDatabase.getByLogin(login);
            if(op == null) {
                throw new HaveNotUserEx();
            }
            return op;
        }
        catch (SQLException ex){
            throw new DBConnectionException();
        }

    }

    public Driver getDriverById(int id) throws DBConnectionException{
        if(!drlist.isEmpty() ){
            for (Driver item : drlist) {
                if (item.getId() == id) {
                    return item;
                }
            }
        }
        try{
            return driverDatabase.getById(id);
        }
        catch (SQLException ex){
            throw new DBConnectionException();
        }

    }

    public List<Driver> getDrivers() throws DBConnectionException{
        if(!drlist.isEmpty() ){
            return drlist;
        }
        else {
            try {
                return driverDatabase.getall();
            } catch (SQLException ex) {
                throw new DBConnectionException();
            }
        }

    }

    public Driver getDriverByLogin(String login) throws DBConnectionException,HaveNotUserEx{
        if(!drlist.isEmpty() ){
            for (Driver item : drlist) {
                if (item.getLogin().equals(login)) {
                    return item;
                }
            }
        }
        try{
            Driver dr = driverDatabase.getByLogin(login);
            if (dr == null)
            {
                throw new HaveNotUserEx();
            }
            return dr;

        }
        catch (SQLException ex){
            throw new DBConnectionException();
        }

    }

    public void addDriver(Driver item) throws DBConnectionException{

        try{
            int id = driverDatabase.add(item);
            if(id != -1)
            {
                item.setId(id);
                drlist.add(item);
            }
            else
            {
                throw new DBConnectionException();
            }
        }
        catch (SQLException ex){
            throw new DBConnectionException();
        }

    }

    public Passenger getPassengerById(int id) throws DBConnectionException{
        if(!paslist.isEmpty() ){
            for (Passenger item : paslist) {
                if (item.getId() == id) {
                    return item;
                }
            }
        }
        try{
            return passengerDatabase.getById(id);
        }
        catch (SQLException ex){
            throw new DBConnectionException();
        }

    }

    public void addPassenger(Passenger item) throws DBConnectionException{

        try{
            int id = passengerDatabase.add(item);
            if(id != -1)
            {
                item.setId(id);
                paslist.add(item);
            }
            else
            {
                throw new DBConnectionException();
            }
        }
        catch (SQLException ex){
            throw new DBConnectionException();
        }

    }

    public Passenger getPassengerByLogin(String login) throws DBConnectionException,HaveNotUserEx{
        if(!paslist.isEmpty() ){
            for (Passenger item : paslist) {
                if (item.getLogin().equals(login)) {
                    return item;
                }
            }
        }
        try{
            Passenger pas = passengerDatabase.getByLogin(login);
            if(pas== null)
            {
                throw new HaveNotUserEx();
            }
            return pas;
        }
        catch (SQLException ex){
            throw new DBConnectionException();
        }

    }

    public Order getOrderById(int id) throws DBConnectionException{
        if(!orlist.isEmpty() ){
            for (Order item : orlist) {
                if (item.getOrderId() == id) {
                    return item;
                }
            }
        }
        try{
            return orderDatabase.getById(id);
        }
        catch (SQLException ex){
            throw new DBConnectionException();
        }

    }

    /*TODO: не забыть положить значения в базу. ВОзможно только после полного формирования заказа(DriverId!)*/
    public void addOrder(Order item) throws DBConnectionException{
       /* try{
            int id = orderDatabase.NewOrder(item);
            if(id != -1)
            {
                item.setOrderId(id);*/
                item.setOrderId(orlist.size()+1);
                orlist.add(item);
            /*}
            else
            {
                throw new DBConnectionException();
            }
        }
        catch (SQLException ex){
            throw new DBConnectionException();
        }
*/
    }

    public void saveOrder(Order item)throws DBConnectionException{
        try{
            item.setExecutionDate(LocalDate.now());
            Driver dr = this.getDriverById(item.getDriver());
            dr.setBusy(false);
            int id = orderDatabase.NewOrder(item);

            if(id != -1)
            {

                item.setOrderId(id);
                orlist.add(item);
            }
            else
            {
                throw new DBConnectionException();
            }
        }
        catch (SQLException ex){
            throw new DBConnectionException();
        }
    }

    public List<Order> getOrders() throws DBConnectionException{
        if (!orlist.isEmpty() ) {
            return orlist;
        }
        else {
            try {
                return orderDatabase.getAllOrders();
            }catch (SQLException ex){
            throw new DBConnectionException();
        }
        }
    }
}
