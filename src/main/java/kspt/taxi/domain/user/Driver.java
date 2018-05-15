package kspt.taxi.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString(exclude = "id")
@EqualsAndHashCode(exclude = "id")
@Slf4j
public class Driver {
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
    @Getter
    @Setter
    private boolean busy;
    @Getter
    @Setter
    private float rating;

    public Driver(int id_, String login_, String name_, String email_, String phone_, float rating_) {
        id = id_;
        login = login_;
        name = name_;
        email = email_;
        authenticated = false;
        phone = phone_;
        busy = false;
        rating = rating_;
    }
}
