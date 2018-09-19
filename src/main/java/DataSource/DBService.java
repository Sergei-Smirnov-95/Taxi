package DataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBService {
    private static Connection connection;
    private static DBService instance;

    public DBService()throws SQLException, IllegalAccessException {
        this.connection = getMysqlConnection();
    }

    public static DBService getInstance()throws SQLException, IllegalAccessException{
        if( instance == null){
           instance = new DBService();
        }
        return instance;
    }
    public static Connection getConnection(){
        return connection;
    }

    private static Connection getMysqlConnection() throws SQLException, IllegalAccessException{
       try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("taxidb?").          //db name
                    append("useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                    System.out.println("URL: " + url + "\n");

            connection = DriverManager.getConnection(url.toString(),"root","Ser210295");
           // System.out.println("Connection ok");
            return connection;
        } catch (SQLException | InstantiationException | ClassNotFoundException e) {
            //e.printStackTrace();
            throw new SQLException();
        } catch ( IllegalAccessException ex){
           throw new IllegalAccessException();
       }


        //return null;
    }
    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
