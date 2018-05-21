package kspt.taxi.repository;

import kspt.taxi.domain.order.Order;
import kspt.taxi.domain.user.Driver;
import kspt.taxi.domain.user.Operator;
import kspt.taxi.domain.user.Passenger;
import org.junit.After;
import org.junit.Before;

public class LogicTest {
    Driver driver;
    Passenger passenger;
    Operator operator;
    Order order;
    Repository repository;

    @Before
    public void setUp() throws Exception{
        repository = new Repository();
        repository.addUser("passanger","passanger","passanger","passanger","passanger");
        repository.addUser("operator","operator","operator","operator","operator");
        repository.addUser("driver","driver","driver","driver","driver");

        passenger = new Passenger(repository.getUser("passenger"));
        passenger.signIn("passenger");
        order = passenger.createOrder("addr","addr");

        operator = new Operator(repository.getUser("operator"));
        operator.signIn("operator");

        driver = new Driver(repository.getUser("driver"));
        driver.signIn("Driver");
    }

    @After
    public void tearDown(){
        repository.clear();
        repository = null;
        passenger = null;
        driver = null;
        operator = null;
        order = null;
    }


}
