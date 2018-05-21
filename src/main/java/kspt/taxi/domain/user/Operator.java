package kspt.taxi.domain.user;

import kspt.taxi.domain.order.Recall;
import kspt.taxi.domain.order.Order;
import kspt.taxi.exceptions.NotAuthenticatedException;
import lombok.extern.java.Log;

import java.util.List;

@Log
public class Operator extends User {
    public Operator(String login_, String name_, String email_, String phone_) {
        super(login_, name_, email_, phone_);
    }

    public Operator(User user) {
        super(user);
    }

    public boolean handleOrder(Order order, float routeLength) throws NotAuthenticatedException {
        checkAuthenticated();
        if (order.getOperator() != null) return false;
        order.appointOperator(this);
        if (routeLength > 0)
            order.getCostCalculation().setRouteLength(routeLength);
        return order.setOrderStatus(Order.OrderStatus.PROCESSING);

    }

    public boolean appointDriverToOrder(Order order, Driver driver) {
        return order.appointDriver(driver);
    }

    public boolean confirmComplaint(Recall recall) {
        if (!recall.getPassengerText().isEmpty()) {
            recall.setConfirmed(true);
            return true;
        }
        return false;
    }
}
