package BusinessLogic;

import org.junit.Before;
import org.junit.Test;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class PassengerTest {
    private Logger log;
    private Order oneorder;
    private Passenger passenger0;
    @Before
    public void init() {
        log = Logger.getLogger("My logger");
        passenger0 = new Passenger(0, "log1", "111","Seriy", "cc11", "3333333");
        oneorder = passenger0.createOrder("afonasievskaya 3","staroderevenskaya 19");
    }
    @Test
    public void createOrder() throws Exception {
        /*Order neworder;
        neworder = passenger0.createOrder("staroderevenskaya 19", "afonasievskaya 3");
        Passenger passenger1;
        //passenger1 = neworder.getPassenger();
        assert(passenger1.toString().equals(passenger0.toString()));
        log.info("original and created order:\n"+passenger1.toString()+"\n"+passenger0.toString());

        assert(neworder.getDestinationAddress().equals("afonasievskaya 3") &&
                neworder.getSourceAddress().equals("staroderevenskaya 19") );

        //log.info(oneorder.toString());
*/
    }

    @Test
    public void declineOrder() throws Exception {
        passenger0.declineOrder(oneorder); //setOrderStatus(OrderStatus.DEAD);
        assert(oneorder.getOrderStatus()==OrderStatus.DEAD);


    }

    @Test
    public void puyBill() throws Exception {
    }

}