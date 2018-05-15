package kspt.taxi.domain.user;

import kspt.taxi.domain.order.Complaint;
import kspt.taxi.domain.order.Order;
import kspt.taxi.domain.order.OrderStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Operator extends User {
    public Operator(int id_, String login_, String name_, String email_, String phone_) {
        super(id_, login_, name_, email_, phone_);
    }

    public boolean getAvailableDriverList(List<Driver> drivers) {
        return true;
    }

    public boolean handleOrder(Order order) {
        return order.setOrderStatus(OrderStatus.PROCESSING) && order.setOperator(this);
    }

    public boolean appointDriverToOrder(Order order, Driver driver) {
        return order.setDriver(driver) && order.setOrderStatus(OrderStatus.APPOINTED);
    }

    public boolean confirmComplaint(Complaint complaint) {
        if (!complaint.getPassengerText().isEmpty()) {
            complaint.setConfirmed(true);
            return true;
        }
        return false;
    }
}
