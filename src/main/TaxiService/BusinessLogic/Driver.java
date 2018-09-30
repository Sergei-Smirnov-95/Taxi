package BusinessLogic;

import Exceptions.HaveNotOrderEx;
import Exceptions.HaveNotUserEx;

import java.util.ArrayList;
import java.util.List;

public class Driver extends User{

    private boolean busy;
    private float rating;


    public Driver(int id_, String login_,String pwd_, String name_, String email_, String phone_, float rating_) {
        super(id_, login_,pwd_, name_, email_, phone_,false);
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

    public boolean loginDriver(String pwd){
        if(this != null)
        {
            String originpwd = this.getPwd();
            if(originpwd.equals(pwd)){
                this.setAuthenticated(true);
                this.setBusy(false);
                return true;
            }
            return false;
        }
        return false;
    }

    public List<Order> getOrderList(List<Order> orderList) throws HaveNotOrderEx {
        List<Order> OrderList = new ArrayList();
        for(Order order : orderList){
            if (order.getDriver() == this.getId() && order.getOrderStatus() != OrderStatus.APPOINTED)
                OrderList.add(order);
        }
        if( OrderList == null )
            throw new HaveNotOrderEx();
        return OrderList;
    }
    public List<Order> getAppointedList(List<Order> orderList) throws HaveNotOrderEx{
        List<Order> appointedOrderList = new ArrayList();
        for(Order order : orderList){
            if (order.getDriver() == this.getId() && order.getOrderStatus() == OrderStatus.APPOINTED)
                appointedOrderList.add(order);
        }
        if( appointedOrderList == null )
            throw new HaveNotOrderEx();
        return appointedOrderList;
    }
    public boolean acceptRequest(Order order){
        if(order.setOrderStatus(OrderStatus.ACCEPTED)) {
            this.setBusy(true);
            order.setDriverId(this.getId());
            return true;
        }
        return false;
    }
    public boolean declineRequest(Order order){
        if(order.setOrderStatus(OrderStatus.DECLINED)) {
            //this.setBusy(false);
            return true;
        }
        return false;
    }
    public void declineOther(List<Order> or) throws HaveNotOrderEx {
        or = this.getAppointedList(or);
        for (Order order : or) {
            if(! declineRequest(order))
            {
                throw new HaveNotOrderEx();
            }
        }
    }
/*
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

    public void calculateCoast(){

    }*/

}
