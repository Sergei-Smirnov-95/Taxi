package DataSource.DBRealisation;

import BusinessLogic.Operator;
import DataSource.DBService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperatorDatabase  {
    private Connection connection;

    public OperatorDatabase()throws SQLException, IllegalAccessException{
        DBService serv= DBService.getInstance();
        connection = serv.getConnection();
    }

    public Operator getById(int id) throws SQLException {
        String selectSQL = "SELECT * FROM User WHERE id = ?;";
        PreparedStatement extractUserStatement = connection.prepareStatement(selectSQL);
        extractUserStatement.setInt(1, id);
        ResultSet rs = extractUserStatement.executeQuery();

        if (!rs.next()) return null;
        //TODO: if item with this id has other type
/*
        if( rs.getInt("TypeUser") !=0){
          throw OtherTypeUserEx ex;
        }
*/
        String login = rs.getString("login");
        String pwd = rs.getString("pwd");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");

        return new Operator(id,login,pwd,name,email,phone);

    }

    public List<Operator> getall() throws SQLException{

        List<Operator> paslst = new ArrayList<>();
        String selectSQL = "SELECT User.id FROM User WHERE TypeUser = 0;";
        PreparedStatement extractUserStatement = connection.prepareStatement(selectSQL);
        ResultSet rs = extractUserStatement.executeQuery();


        while (rs.next()) {
            paslst.add(getById(rs.getInt("id")));
        }

        return paslst;

    }

    public Operator getByLogin(String login) throws SQLException{
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

        return new Operator(id,login,pwd,name,email,phone);

    }

    public int add(Operator item) throws SQLException{
        String insertSQL = "INSERT INTO User(login, pwd, name, email, phone, authenticated, isBusy, rating, TypeUser) "+
                "VALUES (?,?,?,?,?,false, false, 0.0, 0);";
        PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
        insertStatement.setString(1, item.getLogin());
        insertStatement.setString(2, item.getPwd());
        insertStatement.setString(3, item.getName());
        insertStatement.setString(4, item.getEmail());
        insertStatement.setString(5, item.getPhone());
        insertStatement.execute();
        Operator pas = getByLogin(item.getLogin());
        if (pas != null ){
            int id = pas.getId();
            item.setId(id);
            return id;
        }
        return -1;

    }
}
