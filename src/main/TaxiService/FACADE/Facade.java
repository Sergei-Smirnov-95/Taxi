package FACADE;

import BusinessLogic.*;
import DataSource.Repository;
import Exceptions.DBAccessException;
import Exceptions.DBConnectionException;
import Exceptions.HaveNotOrderEx;
import Exceptions.HaveNotUserEx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Facade {
    private static Facade instance;
    private static Repository repository;

    public static Facade getInstance() throws DBConnectionException, DBAccessException  {
        if(instance == null){
            synchronized (Facade.class) {
                if(instance == null){
                    instance = new Facade();
                }
            }
        }
        return instance;
    }
    public Facade() throws DBConnectionException, DBAccessException {
        repository = repository.getInstance();
    }

    public  int authenticate(String login, String pwd) throws DBConnectionException
    {
        if ( loginPassenger(login,pwd)){
            return 1;
        }
        else if(loginOperator(login,pwd)){
            return 0;
        }
        else if(loginDriver(login, pwd)){
            return 2;
        }
        return -1;

    }

    public boolean isUser(String login)throws DBConnectionException{
        try {
            Passenger pass = repository.getPassengerByLogin(login);
            return true;
        } catch (HaveNotUserEx ex){}
        try {
            Driver dr = repository.getDriverByLogin(login);
            return true;
        } catch (HaveNotUserEx ex){}
        try {
            Operator op = repository.getOperatorByLogin(login);
            return true;
        } catch (HaveNotUserEx ex){}
            return false;

    }

    public boolean loginPassenger(String login, String pwd) throws DBConnectionException{
     try{
         Passenger pass = repository.getPassengerByLogin(login);
         return pass.loginPass(pwd);

     } catch ( HaveNotUserEx ex){
         return false;
     }
    }

    public boolean loginDriver(String login, String pwd) throws DBConnectionException{
    try{
        Driver driver = repository.getDriverByLogin(login);
        return driver.loginDriver(pwd);
    } catch ( HaveNotUserEx ex){
        return false;
    }
    }

    public boolean loginOperator(String login, String pwd) throws DBConnectionException{
    try{
        Operator operator = repository.getOperatorByLogin(login);
        return operator.loginOperator(pwd);
    } catch (HaveNotUserEx ex){
        return false;
    }
    }
/*
    public List<Driver> getAvailableDrivers(Operator op){
        List<Driver> drlist = repository.getDrivers();
        return op.getAvailableDrivers(drlist);
    }*/

    public void addNewPassenger(int id_, String login_,String pwd_, String name_, String email_, String phone_) throws DBConnectionException {
        Passenger pas = new Passenger(id_,login_,pwd_,name_,email_,phone_);
            repository.addPassenger(pas);
  }

    public void addNewDriver(int id_, String login_,String pwd_, String name_, String email_, String phone_, float rating_)throws DBConnectionException{
        Driver driver = new Driver(id_,login_,pwd_,name_,email_,phone_, rating_);
            repository.addDriver(driver);
    }

    public void addNewOperator(int id_, String login_,String pwd_, String name_, String email_, String phone_) throws DBConnectionException{
        Operator operator = new Operator(id_,login_,pwd_,name_,email_,phone_);
            repository.addOperator(operator);
    }

    public void addNewOrder(String sourceAddr, String destAddr, String userLogin, LocalDate creationDate_) throws DBConnectionException, HaveNotUserEx {
        int passId = repository.getPassengerByLogin(userLogin).getId();
        Order or = new Order(sourceAddr, destAddr,passId, creationDate_);
        repository.addOrder(or);
    }

    public List<Order> getOrdersByDriver(String login) throws HaveNotOrderEx, HaveNotUserEx, DBConnectionException{
        List<Order> orlist =  repository.getOrders();
        Driver driver = repository.getDriverByLogin(login);
        if( orlist != null ){
            return driver.getOrderList(orlist);
        }
        else
        {
            throw new HaveNotOrderEx();
        }
    }

    public List<Order> getAppointedOrdersByDriver(String login)throws HaveNotOrderEx, HaveNotUserEx,DBConnectionException{
        List<Order> orlist =  repository.getOrders();
        Driver driver = repository.getDriverByLogin(login);
        if( orlist != null ){
          return driver.getAppointedList(orlist);}
        else
        {
            throw new HaveNotOrderEx();
        }

    }
    public void acceptRequest(int OrderId, String driverLogin) throws DBConnectionException{

            Order or = repository.getOrderById(OrderId);
            or.setOrderStatus(OrderStatus.ACCEPTED);

    }
    public void declineRequest(int OrderId, String driverLogin) throws DBConnectionException{

        Order or = repository.getOrderById(OrderId);
        or.setOrderStatus(OrderStatus.DECLINED);

    }
}
