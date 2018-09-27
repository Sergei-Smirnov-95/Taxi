
import BusinessLogic.Passenger;
import DataSource.DBRealisation.OperatorDatabase;
import DataSource.DBRealisation.PassengerDatabase;
import GUI.DriverForm;
import GUI.Login;
import GUI.NewUserForm;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class Main {
    static  private String currentLogin;
    public static void main(String... args) {
        Login login = new Login();
        login.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }



}
        /*//Add new passangers
        Passenger passenger0 = new Passenger(0, "log1", "Seriy", "cc11", "3333333");
        Passenger passenger1 = new Passenger(1, "log2", "Volk", "bb11", "444444");
        //Create new repository
        PassengerRepository passengerRepository = new PassengerRepository();
        List<Passenger> paslst;
        OrderRepository orderRepository = new OrderRepository();
        List<Order> orlist;
        Order oneorder;
        try {
            //Add passangers to repo
            passengerRepository.create(passenger0);
            passengerRepository.add(passenger1);
            //Create new order and add it to repo
            oneorder = passenger0.createOrder("staroderevenskaya 19","afonasievskaya 3");
            orderRepository.create(oneorder);

            //Read and print repos info
            orlist = orderRepository.Getall();
            for (Order item:orlist) {
                System.out.println(item);
            }

            paslst = passengerRepository.Getall();
            for (Passenger pas:paslst) {
                System.out.println(pas);
            }
        }
        catch(DatabaseException DataEx)
        {
            System.out.println("Database Exception!");
        }*/
///////////
        /*PassengerDatabase pasdb = new PassengerDatabase();
        OperatorDatabase opdb = new OperatorDatabase();
        Passenger passenger1 = new Passenger(1, "gen", "1122","Gena", "bb11", "444444");

        List<Passenger> paslst;
        try {
            paslst = pasdb.getall();
            for (Passenger pas:paslst) {
                System.out.println(pas);
            }

        }
        catch (SQLException ex1){

            System.out.println("SQL Exception!");
        }
        */