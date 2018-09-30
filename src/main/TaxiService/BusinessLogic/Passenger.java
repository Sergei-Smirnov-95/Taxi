package BusinessLogic;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Passenger extends User {

   public Passenger(int id_, String login_,String pwd_, String name_, String email_, String phone_) {
        super(id_, login_,pwd_, name_, email_, phone_,false);
    }

    public Order createOrder( String sourceAddress, String destinationAddress){
        Order order = new Order(sourceAddress,destinationAddress,this.getId(),
                new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return order;
    }

    public boolean loginPass(String pwd){
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

    boolean declineOrder(Order order){
        //System.out.println("Order declined by passenger");
        return order.setOrderStatus(OrderStatus.DEAD);
    }

    void puyBill(Order or){
        or.pay();
    }

}
