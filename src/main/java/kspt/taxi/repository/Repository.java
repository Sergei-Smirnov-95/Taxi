package kspt.taxi.repository;

import com.sun.org.apache.xpath.internal.operations.Or;
import kspt.taxi.domain.order.Order;
import kspt.taxi.domain.user.Driver;
import kspt.taxi.domain.user.Passenger;
import kspt.taxi.domain.user.User;
import kspt.taxi.exceptions.DatabaseException;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import sun.reflect.generics.repository.AbstractRepository;

import javax.management.Query;
import java.util.*;

@Log
public class Repository {

    private static Map<String, User> users = new HashMap<>();
    private static Map<Integer, Order> orders = new HashMap<>();
    private static Map<String, String> passwds = new HashMap<>();

    public Repository() {
    }

    public boolean addUser(String login, String name, String email, String phone, String password) {
        if (users.containsKey(login)) return false;

        User newUser = new User(login, name, email, phone);
        passwds.put(login, password);
        return true;
    }

    public User getUser(String login) {
        return users.get(login);
    }

    public boolean authenticateUser(User user, String password) {
        return passwds.get(user.getLogin()).equals(password);
    }

    public Order addOrder(int id, String sourceAddress, String destinationAddress, Passenger passenger) {
        Order order = new Order(sourceAddress, destinationAddress, passenger);
        orders.put(id, order);
        return order;
    }

    public void clear() {
        users.clear();
        passwds.clear();
        orders.clear();
    }
}
