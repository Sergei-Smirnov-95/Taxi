package BusinessLogic;

import Repository.DatabaseException;
import Repository.RepoRealisation.DriverRepository;

import java.util.List;

public class Operator extends User {
    public Operator(int id_, String login_,String pwd_, String name_, String email_, String phone_) {
        super(id_, login_, pwd_, name_, email_, phone_,false);
    }

    public List<Driver> getAvailableDriverList(DriverRepository repo){
        List<Driver> lst= null ;
        try{
            lst = repo.Getall();
            for(Driver item:lst)
            {
              if(item.isBusy())
                  lst.remove(item);
            }
            return lst;
        }
        catch(DatabaseException DataEx)
        {
            return lst;
        }
    }

    public boolean handleOrder(Order order){
        return order.setOrderStatus(OrderStatus.PROCESSING) && order.setOperator(this);
    }

    public boolean appointDriverToOrder(Order order, Driver driver){
        return order.setDriver(driver) && order.setOrderStatus(OrderStatus.APPOINTED);
    }

    public boolean confirmComplaint(Complaint complaint){
        if(!complaint.getPassengerText().isEmpty()) {
            complaint.setConfirmed(true);
            return true;
        }
        return false;
    }
}
