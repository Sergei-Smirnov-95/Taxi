import BusinessLogic.Order;
import BusinessLogic.Passenger;
import Exceptions.DatabaseException;
import Repository.DBRealisation.PassengerDatabase;
import Repository.DBService;
import Repository.RepoRealisation.OrderRepository;
import Repository.RepoRealisation.PassengerRepository;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String... args) {
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
        PassengerDatabase pasdb = new PassengerDatabase();
        pasdb.printConnectInfo();
        List<Passenger> paslst;
        try {
            Passenger pas0 = pasdb.getById(1);
            //System.out.println(pas0);
            paslst = pasdb.getall();
            for (Passenger pas:paslst) {
                System.out.println(pas);
            }
        }
        catch (SQLException ex1){

            System.out.println("SQL Exception!");
        }

    }
}
