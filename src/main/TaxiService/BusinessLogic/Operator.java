package BusinessLogic;

import java.util.List;

public class Operator extends User {
    public Operator(int id_, String login_,String pwd_, String name_, String email_, String phone_) {
        super(id_, login_, pwd_, name_, email_, phone_,false);
    }

    public boolean handleOrder(Order order){
        order.setOperator(this.getId());
        return order.setOrderStatus(OrderStatus.PROCESSING) ;
    }

    public boolean loginOperator(String pwd){
        if(this != null)
        {
            String originpwd = this.getPwd();
            if(originpwd.equals(pwd)){
                this.setAuthenticated(true);
                return true;
            }
            return false;
        }
        return false;
    }
    public List<Driver> getAvailableDrivers(List<Driver> drlist){
        for (Driver item : drlist) {
            if (item.isBusy() == true) {
                drlist.remove(item);
            }
        }
        return drlist;
    }
/*
    public boolean appointDriverToOrder(Order order, int driver){
        return order.setDriverId(driver) && order.setOrderStatus(OrderStatus.APPOINTED);
    }
*/
    public boolean confirmComplaint(Complaint complaint){
        if(!complaint.getPassengerText().isEmpty()) {
            complaint.setConfirmed(true);
            return true;
        }
        return false;
    }
}
