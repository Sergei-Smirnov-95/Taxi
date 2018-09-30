package BusinessLogic;

import java.util.ArrayList;
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
        List<Driver> drl =  new ArrayList();
        for (Driver item : drlist) {
            if (item.isBusy() != true) {
                drl.add(item);
            }
        }
        //drlist.clear();
        return drl;
    }

    public List<Order> getNewOrders(List<Order> orlist){
        List<Order> orl = new ArrayList();
        for(Order or:orlist){
            //System.out.println(or.getOrderStatus());
            if(or.getOrderStatus().equals(OrderStatus.NEW) || (or.getOrderStatus().equals(OrderStatus.PROCESSING))) {
                or.setOrderStatus(OrderStatus.PROCESSING);
                orl.add(or);
            }
        }
        //orlist.clear();
        return orl;
    }

    public void appointOrder(int selectedDriver,Order or) {
        or.setDriverId(selectedDriver);
        or.setOrderStatus(OrderStatus.APPOINTED);
        or.setOperator(this.getId());
    }
    public boolean confirmComplaint(Complaint complaint){
        if(!complaint.getPassengerText().isEmpty()) {
            complaint.setConfirmed(true);
            return true;
        }
        return false;
    }
}
