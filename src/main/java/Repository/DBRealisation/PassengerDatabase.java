package Repository.DBRealisation;

import BusinessLogic.Passenger;
import BusinessLogic.User;
import Exceptions.DatabaseException;
import Repository.DBService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDatabase extends DBService<Passenger> {
    @Override
    public Passenger getById(int id) throws SQLException{
        String selectSQL = "SELECT * FROM User WHERE id = ?;";
        PreparedStatement extractUserStatement = connection.prepareStatement(selectSQL);
        extractUserStatement.setInt(1, id);
        ResultSet rs = extractUserStatement.executeQuery();

        if (!rs.next()) return null;

        String login = rs.getString("login");
        String pwd = rs.getString("pwd");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        boolean authenticated = rs.getBoolean("");

        return (Passenger) new User(id,login,pwd,name,email,phone,authenticated);

    }

    public List<Passenger> getall() throws SQLException{

        List<Passenger> paslst = new ArrayList<>();
        String selectSQL = "SELECT User.id FROM User WHERE TypeUser = 1;";
        PreparedStatement extractUserStatement = connection.prepareStatement(selectSQL);
        ResultSet rs = extractUserStatement.executeQuery();


        while (rs.next()) {
            paslst.add(getById(rs.getInt("id")));
        }

        return paslst;

    }
    public void add(Passenger item) throws DatabaseException{}
}
