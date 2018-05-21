package kspt.taxi.domain.user;


import kspt.taxi.domain.order.Order;
import kspt.taxi.exceptions.NotAuthenticatedException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@ToString(exclude = "id")
@EqualsAndHashCode(exclude = "id")
@Log
public abstract class User {
    @Getter
    protected int id;

    @Getter
    protected String login;

    @Getter
    protected String name;

    @Getter
    @Setter
    protected String email;

    @Getter
    @Setter
    protected String phone;

    @Getter
    @Setter
    protected boolean authenticated;

    @Getter
    protected List<Order> orders;

    public User(String login_, String name_, String email_, String phone_) {
        login = login_;
        name = name_;
        email = email_;
        authenticated = false;
        phone = phone_;
        orders = new ArrayList();
    }

    public boolean signIn(String password) {
        return authenticated;
    }

    public void signOut() {
        authenticated = false;
    }

    public User getUser() {
        return this;
    }

   void checkAuthenticated() throws NotAuthenticatedException {
        if (isAuthenticated()) return;
        throw new NotAuthenticatedException(toString() + " is not authenticated");
    }
}
