package BusinessLogic;

import java.util.Date;
import java.util.List;

public class Passenger extends User {
    public Passenger(int id_, String login_, String name_, String email_, String phone_) {
        super(id_, login_, name_, email_, phone_);
    }

    void createOrder(List<Order> orderList, String sourceAddress, String destinationAddress){
        Order order = new Order(sourceAddress,destinationAddress,this, new Date());
        orderList.add(order);
    }

    boolean declineOrder(Order order){
        return order.setOrderStatus(OrderStatus.DEAD);
    }


}
