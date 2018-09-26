package DataSource.DBRealisation;

import BusinessLogic.Passenger;
import DataSource.DBService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDatabase  {
    private Connection connection;

    public PassengerDatabase()throws SQLException, IllegalAccessException{
        DBService serv= DBService.getInstance();
        connection = serv.getConnection();
    }

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

        return new Passenger(id,login,pwd,name,email,phone);

    }

    public Passenger getByLogin(String login) throws SQLException{
        String selectSQL = "SELECT * FROM User WHERE login = ?;";
        PreparedStatement extractUserStatement = connection.prepareStatement(selectSQL);
        extractUserStatement.setString(1, login);
        ResultSet rs = extractUserStatement.executeQuery();

        if (!rs.next()) return null;

        int id = rs.getInt("id");
        String pwd = rs.getString("pwd");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");

        return new Passenger(id,login,pwd,name,email,phone);

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

    public int add(Passenger item) throws SQLException{
        String login = item.getLogin();
        if(getByLogin(login)!=null)
        {
            return -2;
        }
        else {
            String insertSQL = "INSERT INTO User(login, pwd, name, email, phone, authenticated, isBusy, rating, TypeUser) " +
                    "VALUES (?,?,?,?,?,false, false, 0.0, 1);";
            PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
            insertStatement.setString(1, item.getLogin());
            insertStatement.setString(2, item.getPwd());
            insertStatement.setString(3, item.getName());
            insertStatement.setString(4, item.getEmail());
            insertStatement.setString(5, item.getPhone());
            insertStatement.execute();

            Passenger pas = getByLogin(item.getLogin());
            if (pas != null) {
                int id = pas.getId();
                item.setId(id);
                return id;
            }
            return -1;
        }
    }
}
