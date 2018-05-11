package kspt.taxi.domain.user;


import kspt.taxi.exceptions.NotAuthenticatedException;

public abstract class User {
    private int id;
    private String login;
    private String name;
    private String email;
    private String phone;
    private boolean authenticated;

    public User(int id_, String login_, String name_, String email_, String phone_) {
        id = id_;
        login = login_;
        name = name_;
        email = email_;
        authenticated = false;
        phone = phone_;

    }

    public boolean signIn(String password) {
        //assert (!authenticated);
        //authenticated = (new StorageRepository()).authenticateUser(login, password);
        return authenticated;
    }

    public void signOut() {
        authenticated = false;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return login + ":" + name + "<" + email + ">" + "<" + phone + ">";
    }

    public User getUser() {
        return this;
    }

    public void checkAuthenticated() throws NotAuthenticatedException {
        if (isAuthenticated()) return;
        throw new NotAuthenticatedException(toString() + " is not authenticated");
    }
}
