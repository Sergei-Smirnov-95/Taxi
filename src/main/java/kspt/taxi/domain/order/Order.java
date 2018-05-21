package kspt.taxi.domain.order;

import kspt.taxi.domain.user.Driver;
import kspt.taxi.domain.user.Operator;
import kspt.taxi.domain.user.Passenger;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.Calendar;
import java.util.Date;

@ToString(exclude = "id")
@EqualsAndHashCode(exclude = "id")
@Log
public class Order {
    @Getter
    private int id;
    @Getter
    @Setter
    private String sourceAddress;
    @Getter
    @Setter
    private String destinationAddress;
    @Getter
    @Setter
    private Passenger passenger;
    @Getter
    @Setter
    private Driver driver;
    @Getter
    @Setter
    private Operator operator;
    @Getter
    @Setter
    private OrderStatus orderStatus;
    @Getter
    @Setter
    private float rating;
    @Getter
    @Setter
    private Date creationDate;
    @Getter
    @Setter
    private Date executionDate;
    @Getter
    @Setter
    private CostCalculation costCalculation;
    @Getter
    @Setter
    private Recall recall;

    public Order(String sourceAddress, String destinationAddress, Passenger passenger) {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.passenger = passenger;
        this.orderStatus = OrderStatus.NEW;
        this.creationDate = Calendar.getInstance().getTime();
        this.costCalculation = new CostCalculation();
    }

    public boolean declineOrder() {
        return setOrderStatus(OrderStatus.DEAD);
    }


    public boolean appointOperator(Operator operator) {
        setOperator(operator);
        return setOrderStatus(OrderStatus.PROCESSING);
    }

    public boolean appointDriver(Driver driver) {
        setDriver(driver);
        return setOrderStatus(OrderStatus.APPOINTED);
    }

    public boolean confirmDriver() {
        return setOrderStatus(OrderStatus.ACCEPTED);
    }

    public boolean rejectDriver(){
        setDriver(null);
        return setOrderStatus(OrderStatus.REJECTED);
    }

    public boolean completeOrder(){
        Date executeDate = Calendar.getInstance().getTime();
        if (executeDate.after(creationDate)) {
            setExecutionDate(executeDate);
            return setOrderStatus(OrderStatus.EXECUTED);
        }
        else return false;
    }

    public boolean setOrderStatus(OrderStatus orderStatus) {
        if (!OrderStatus.isAvailable(this.orderStatus, orderStatus)) return false;
        this.orderStatus = orderStatus;
        return true;
    }

    public enum OrderStatus {
        /* Just created by passenger */
        /* Can go to: PROCESSING, DEAD */
        NEW("NEW"),

        /* Processing by operator: choosing a driver */
        /* Can go to: APPOINTED, DEAD */
        PROCESSING("PROCESSING"),

        /* Appointed to driver */
        /* Can go to: PROCESSING, DECLINED */
        APPOINTED("APPOINTED"),

        /* Declined by driver */
        /* Can go to: PROCESSING */
        REJECTED("DECLINED"),

        /* Accepted by driver */
        /* Can go to: EXECUTED */
        ACCEPTED("ACCEPTED"),

        /* Order is executed */
        /* Can go to: <NULL> */
        EXECUTED("EXECUTED"),

        /* No available drivers or killed by passenger */
        /* Can go to: <NULL> */
        DEAD("DEAD");

        private final String id;

        OrderStatus(final String id) {
            this.id = id != null ? id : "NEW";
        }

        public String getId() {
            return id;
        }

        static boolean isAvailable(final OrderStatus previousState, final OrderStatus nextState) {
            boolean result = false;
            switch (previousState) {
                case NEW:
                    result = nextState == PROCESSING || nextState == DEAD;
                    break;
                case PROCESSING:
                    result = nextState == APPOINTED || nextState == DEAD;
                    break;
                case APPOINTED:
                    result = nextState == ACCEPTED || nextState == REJECTED;
                    break;
                case REJECTED:
                    result = nextState == PROCESSING;
                    break;
                case ACCEPTED:
                    result = nextState == EXECUTED;
                    break;
            }
            return result;
        }
    }
}
