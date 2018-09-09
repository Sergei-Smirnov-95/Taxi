package Repository.DBRealisation;

import BusinessLogic.Operator;
import Repository.DBService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperatorDatabase extends DBService<Operator> {
    @Override
    public Operator getById(int id) throws SQLException {
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

        return new Operator(id,login,pwd,name,email,phone);

    }

    public List<Operator> getall() throws SQLException{

        List<Operator> paslst = new ArrayList<>();
        String selectSQL = "SELECT User.id FROM User WHERE TypeUser = 1;";
        PreparedStatement extractUserStatement = connection.prepareStatement(selectSQL);
        ResultSet rs = extractUserStatement.executeQuery();


        while (rs.next()) {
            paslst.add(getById(rs.getInt("id")));
        }

        return paslst;

    }
    public int add(Operator item) throws SQLException{
        String insertSQL = "INSERT INTO `User`(login, pwd, name, email, phone, authenticated, isBusy, rating, TypeUser) "+
                "VALUES (\"?\",\"?\",\"?\",\"?\",\"?\",false, false, 0.0, 1);";
        PreparedStatement insertStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
        insertStatement.setString(1, item.getLogin());
        insertStatement.setString(2, item.getPwd());
        insertStatement.setString(3, item.getName());
        insertStatement.setString(4, item.getEmail());
        insertStatement.setString(5, item.getPhone());
        insertStatement.execute();
        ResultSet rs = insertStatement.getGeneratedKeys();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("id");
            item.setId((int) id);
        }
        return id;

    }
}
