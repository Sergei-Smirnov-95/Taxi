package BusinessLogic;


public class User {
    private int id;
    private String login;
    private String pwd;
    private String name;
    private String email;
    private String phone;
    private boolean authenticated;

    public User(int id_, String login_,String pwd_, String name_, String email_, String phone_, boolean authenticated_) {
        id = id_;
        login = login_;
        name = name_;
        email = email_;
        authenticated = false;
        phone = phone_;
        pwd = pwd_;
        authenticated = authenticated_;
    }

    public String getLogin() {
        return login;
    }

    public String getPwd(){return pwd;}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id){
        this.id=id;
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

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", authenticated=" + authenticated +
                '}';
    }
}
