package kspt.taxi.domain.user;

import kspt.taxi.domain.order.Order;
import kspt.taxi.domain.order.Recall;
import kspt.taxi.exceptions.NoRightsException;
import kspt.taxi.exceptions.NotAuthenticatedException;

public class Passenger extends User {

    public Passenger(int id_, String login_, String name_, String email_, String phone_) {
        super(login_, name_, email_, phone_);
    }

    public Order createOrder(String sourceAddress, String destinationAddress) throws NotAuthenticatedException {
        checkAuthenticated();
        Order order = new Order(sourceAddress, destinationAddress, this);
        if (!orders.contains(order))
            orders.add(order);
        return order;
    }

    public void declineOrder(Order order) throws NotAuthenticatedException, NoRightsException {
        checkAuthenticated();
        if (order.getPassenger() != this)
            throw new NoRightsException("User " + getLogin() + "cannot change order " + order.getId());
        order.declineOrder();
    }

    public boolean addRecall(Order order, String recallText, int score) throws NotAuthenticatedException, NoRightsException {
        checkAuthenticated();
        Recall recall = new Recall(recallText, score);
        if (order.getPassenger() != this)
            throw new NoRightsException("User " + getLogin() + "cannot change order " + order.getId());
        if (order.getRecall() != null && score >= Driver.MIN_SCORE && score <= Driver.MAX_SCORE) {
            order.setRecall(recall);
            return true;
        }
        else return false;
    }
}
