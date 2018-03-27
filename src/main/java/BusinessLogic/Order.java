package BusinessLogic;

import java.util.Date;

public class Order {

    private String sourceAddress;
    private String destinationAddress;
    private Passenger passenger;
    private Driver driver;
    private Operator operator;
    private OrderStatus orderStatus;
    private float rating;
    private Date creationDate;
    private Date executionDate;
    private CostCalculation costCalculation;
    private Complaint complaint;

    public Order(String sourceAddress, String destinationAddress, Passenger passenger, Date creationDate) {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.passenger = passenger;
        this.orderStatus = OrderStatus.NEW;
        this.creationDate = creationDate;
        this.costCalculation = new CostCalculation();
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public Operator getOperator() {
        return operator;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public float getRating() {
        return rating;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public CostCalculation getCostCalculation() {
        return costCalculation;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public boolean setPassenger(Passenger passenger) {
        if(passenger != null) {
            this.passenger = passenger;
            return true;
        }
        return false;
    }

    public boolean setDriver(Driver driver) {
        if (driver != null && driver.isAvailable()) {
            this.driver = driver;
            return true;
        }
        return false;
    }

    public boolean setOperator(Operator operator) {
        if(operator != null) {
            this.operator = operator;
            return true;
        }
        return false;
    }

    public boolean setOrderStatus(OrderStatus orderStatus) {
        if(OrderStatus.isAvailable(this.orderStatus, orderStatus)){
            this.orderStatus = orderStatus;
            return true;
        }
        return false;
    }

    public boolean setRating(float rating) {
        if(rating > 0 && rating <= 5) {
            this.rating = rating;
            return true;
        }
        return false;
    }

    public boolean setCreationDate(Date creationDate) {
        if(creationDate.before(new Date())) {
            this.creationDate = creationDate;
            return true;
        }
        return false;
    }

    public boolean setExecutionDate(Date executionDate) {
        if(executionDate.after(creationDate)) {
            this.executionDate = executionDate;
            return true;
        }
        return false;
    }

    public void setCostCalculation(CostCalculation costCalculation) {
        this.costCalculation = costCalculation;
    }
}
