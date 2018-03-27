package BusinessLogic;

import java.util.ArrayList;
import java.util.List;

public class Driver extends User{

    private boolean busy;
    private float rating;


    public Driver(int id_, String login_, String name_, String email_, String phone_, float rating_) {
        super(id_, login_, name_, email_, phone_);
        busy = false;
        rating = rating_;
    }

    public boolean isBusy() {
        return busy;
    }

    public boolean isAvailable() {
        return !busy;
    }

    public float getRating() {
        return rating;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Order> getOrderList(List<Order> orderList){
        List<Order> appointedOrderList = new ArrayList();
        for(Order order : orderList){
            if (order.getDriver() == this && order.getOrderStatus() == OrderStatus.APPOINTED)
                appointedOrderList.add(order);
        }
        return appointedOrderList;
    }

    public boolean acceptOrder(Order order){
        if(order.setOrderStatus(OrderStatus.ACCEPTED)) {
            setBusy(true);
            return true;
        }
        return false;

    }

    public boolean declineOrder(Order order){
        if(order.setOrderStatus(OrderStatus.DECLINED)) {
            setBusy(false);
            return true;
        }
        return false;
    }

    public boolean setRouteLength(Order order, float routeLength){
        return order.getCostCalculation().setRouteLength(routeLength);
    }

    public void setWaitingTime(){

    }

    public void refuteComplain(){

    }

}
