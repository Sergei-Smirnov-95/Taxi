package BusinessLogic;

import java.util.Date;
import java.util.List;

public class Passenger extends User {

   public Passenger(int id_, String login_,String pwd_, String name_, String email_, String phone_) {
        super(id_, login_,pwd_, name_, email_, phone_,false);
    }

    public Order createOrder( String sourceAddress, String destinationAddress){
        Order order = new Order(sourceAddress,destinationAddress,this, new Date());
        //orderList.add(order);
        //System.out.println("Order created by "+this.getName());
        return order;
    }



    boolean declineOrder(Order order){
        //System.out.println("Order declined by passenger");
        return order.setOrderStatus(OrderStatus.DEAD);
    }

    void puyBill(){

    }

}
