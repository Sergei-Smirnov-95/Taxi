package BusinessLogic;

import java.time.LocalDate;

public class Order {

    private String sourceAddress;
    private String destinationAddress;
    private int passengerId;
    private int driverId;
    private int operatorId;
    private OrderStatus orderStatus;
//    private float rating;
    private LocalDate creationDate;
    private LocalDate executionDate;
    private int id;
    private boolean isPaied;
    private CostCalculation costCalculation;
    private Complaint complaint;


    public Order(String sourceAddress, String destinationAddress,int passengerId , LocalDate creationDate) {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.passengerId = passengerId;
        this.orderStatus = OrderStatus.NEW;
        this.creationDate = creationDate;
        this.costCalculation = new CostCalculation(0,0);
        this.isPaied= false;
    }

    public Order restoreOrder( int driverId, int operatorId,
                        OrderStatus orderStatus, LocalDate executionDate, int id, float routelength,
                             float waitingTime, float totalCost, String Complaint, boolean isPaied){
        this.setDriverId(driverId);
        this.setOperator(operatorId);
        this.setStatus(orderStatus);
        this.setOrderId(id);
        this.setExecutionDate(executionDate);
        this.setCostCalculation(waitingTime,routelength);
        this.isPaied = isPaied;
        return this ;
    }
    public String getSourceAddress() {
        return sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public int getPassenger() {
        return passengerId;
    }

    public int getDriver() {
        return driverId;
    }

    public int getOperator() {
        return operatorId;
    }

    public boolean isPayed() {return isPaied;}
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }


    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getExecutionDate() {
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

    public void setPassengerId(int passenger) {
            this.passengerId = passenger;
    }

    public void setDriverId(int driver) {
            this.driverId = driver;
    }

    public void setOperator(int operator) {
            this.operatorId = operator;
    }

    private void setStatus(OrderStatus os){
        this.orderStatus = os;
    }

    public boolean setOrderStatus(OrderStatus orderStatus) {
        if(OrderStatus.isAvailable(this.orderStatus, orderStatus)){
            this.orderStatus = orderStatus;
            return true;
        }
        return false;
    }
    public void pay(){
        isPaied = true;
    }


    public boolean setExecutionDate(LocalDate executionDate) {
        //if(executionDate.isAfter(creationDate)) {
            this.executionDate = executionDate;
            return true;
        //}
        //return false;
    }

    public void setCostCalculation(float waitingTime,float routelength) {
        CostCalculation cc =new CostCalculation(waitingTime,routelength);
        this.costCalculation = cc;
        this.pay();
    }

    public void setOrderId(int id){
        this.id = id;
    }

    public int getOrderId(){
        return this.id;
    }
    @Override
    public String toString() {
        return "Order by "+
                //passenger.getName()+
                " from "+sourceAddress +
                " to "+ destinationAddress+
                ". Date:"+ creationDate;
    }
}
