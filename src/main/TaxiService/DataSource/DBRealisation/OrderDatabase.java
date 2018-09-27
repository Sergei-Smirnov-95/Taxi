package DataSource.DBRealisation;

import BusinessLogic.Order;
import BusinessLogic.OrderStatus;
import DataSource.DBService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDatabase {
    private Connection connection;

    public OrderDatabase()throws SQLException, IllegalAccessException{
        DBService serv= DBService.getInstance();
        connection = serv.getConnection();
    }

    public int NewOrder(Order item)throws SQLException {
        // Order(String sourceAddress, String destinationAddress, Passenger passenger, Date creationDate
            String insertSQL = "INSERT INTO Order(sourceAddr, destAddr, creationDate, executionDate,"+
                    " driverId, passId, operatorId, routeLength, waitingTime, totalCost,isPayed) " +
                    "VALUES (?,?,?,'1000-01-01 00:00:00',?,?,?, 0.0,0.0,0.0, false);";
            PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
            insertStatement.setString(1, item.getSourceAddress());
            insertStatement.setString(2, item.getDestinationAddress());
            insertStatement.setDate(3, java.sql.Date.valueOf(item.getCreationDate()));
            //insertStatement.setDate(4,  java.sql.Date.valueOf(item.getExecutionDate()));
            insertStatement.setInt(4, item.getDriver());
            insertStatement.setInt(5, item.getPassenger());
            insertStatement.setInt(6, item.getOperator());

        insertStatement.execute();

            Order check = getByDate(item.getCreationDate(),item.getSourceAddress());
            if (check != null) {
                int id = check.getOrderId();
                item.setOrderId(id);
                return id;
            }
            return -1;
    }

    public Order getByDate(LocalDate date, String addr) throws SQLException{
        String selectSQL = "SELECT * FROM Order WHERE creationDate = ? AND sourceAddr = ?;";
        PreparedStatement extractUserStatement = connection.prepareStatement(selectSQL);
        extractUserStatement.setDate(1, java.sql.Date.valueOf(date));
        extractUserStatement.setString(2, addr);

        ResultSet rs = extractUserStatement.executeQuery();

        if (!rs.next()) return null;
        String sourceAddr = rs.getString("sourceAddr");
        String destAddr = rs.getString("destAddr");
        LocalDate creationDate = rs.getDate("creationDate").toLocalDate();
        LocalDate executionDate = rs.getDate("executionDate").toLocalDate();
        int driverId = rs.getInt("driverId");
        int passId = rs.getInt("passId");
        int operatorId = rs.getInt("operatorId");
        float routeLength = rs.getFloat("routeLength");
        float waitingTime = rs.getFloat("waitingTIme");
        float totalCost = rs.getFloat("totalCost");
        boolean isPayed = rs.getBoolean("isPayed");
        String complaint = rs.getString("complaint");
        int id = rs.getInt("id");
        Order or = new Order(sourceAddr, destAddr,passId, creationDate);
        if(isPayed){
            return  or.restoreOrder(driverId, operatorId,
                    OrderStatus.EXECUTED, executionDate, id,  routeLength, waitingTime, totalCost, complaint, true);
        }
        else
            return  or.restoreOrder(driverId, operatorId,
                    OrderStatus.PROCESSING, executionDate, id,  routeLength, waitingTime, totalCost, complaint, false);

    }
    public Order getById(int id) throws SQLException {
        String selectSQL = "SELECT * FROM Order WHERE id= ?;";
        PreparedStatement extractUserStatement = connection.prepareStatement(selectSQL);
        extractUserStatement.setInt(1, id);
        ResultSet rs = extractUserStatement.executeQuery();

        if (!rs.next()) return null;
        String sourceAddr = rs.getString("sourceAddr");
        String destAddr = rs.getString("destAddr");
        LocalDate creationDate = rs.getDate("creationDate").toLocalDate();
        LocalDate executionDate = rs.getDate("executionDate").toLocalDate();
        int driverId = rs.getInt("driverId");
        int passId = rs.getInt("passId");
        int operatorId = rs.getInt("operatorId");
        float routeLength = rs.getFloat("routeLength");
        float waitingTime = rs.getFloat("waitingTIme");
        float totalCost = rs.getFloat("totalCost");
        boolean isPayed = rs.getBoolean("isPayed");
        String complaint = rs.getString("complaint");
        Order or = new Order(sourceAddr, destAddr,passId, creationDate);
        if(isPayed){
            return  or.restoreOrder(driverId, operatorId,
                    OrderStatus.EXECUTED, executionDate, id,  routeLength, waitingTime, totalCost, complaint, true);
        }
        else
            return  or.restoreOrder(driverId, operatorId,
                    OrderStatus.PROCESSING, executionDate, id,  routeLength, waitingTime, totalCost, complaint, false);

    }

    public List<Order> getAllOrders() throws SQLException{
        String selectSQL = "SELECT * FROM Order;";
        PreparedStatement extractUserStatement = connection.prepareStatement(selectSQL);
        ResultSet rs = extractUserStatement.executeQuery();
        List<Order> orlist = new ArrayList();
        while (rs.next()) {
            String sourceAddr = rs.getString("sourceAddr");
            String destAddr = rs.getString("destAddr");
            LocalDate creationDate = rs.getDate("creationDate").toLocalDate();
            LocalDate executionDate = rs.getDate("executionDate").toLocalDate();
            int driverId = rs.getInt("driverId");
            int passId = rs.getInt("passId");
            int operatorId = rs.getInt("operatorId");
            int id = rs.getInt("id");
            float routeLength = rs.getFloat("routeLength");
            float waitingTime = rs.getFloat("waitingTIme");
            float totalCost = rs.getFloat("totalCost");
            boolean isPayed = rs.getBoolean("isPayed");
            String complaint = rs.getString("complaint");
            Order or = new Order(sourceAddr, destAddr, passId, creationDate);

            if (isPayed) {
                orlist.add( or.restoreOrder(driverId, operatorId,
                        OrderStatus.EXECUTED, executionDate, id, routeLength,
                        waitingTime, totalCost, complaint, true));
            } else
                orlist.add(or.restoreOrder(driverId, operatorId,
                        OrderStatus.PROCESSING, executionDate, id,
                        routeLength, waitingTime, totalCost, complaint, false));
        }
        return orlist;
    }
}
