package BusinessLogic;

public class Bill {
    private double coast;
    private boolean isPayed;

    public Bill(double OrderCoast){
        coast = OrderCoast;
        isPayed = false;
    }
    private void pay(){
        isPayed = true;
    }
}
