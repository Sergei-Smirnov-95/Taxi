package kspt.taxi.domain.user;


import kspt.taxi.exceptions.NotAuthenticatedException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString(exclude = "id")
@EqualsAndHashCode(exclude = "id")
@Slf4j
public abstract class User {
    @Getter
    private int id;
    @Getter
    private String login;
    @Getter
    private String name;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String phone;
    @Getter
    @Setter
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

    public boolean isAuthenticated() {
        return authenticated;
    }

    public User getUser() {
        return this;
    }

    public void checkAuthenticated() throws NotAuthenticatedException {
        if (isAuthenticated()) return;
        throw new NotAuthenticatedException(toString() + " is not authenticated");
    }
}
