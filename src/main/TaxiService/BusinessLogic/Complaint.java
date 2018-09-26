package BusinessLogic;

public class Complaint {
    private String textComplaint;
    private boolean isConfirmed;

    public String getPassengerText(){
        return textComplaint;
    }
    public void setConfirmed(boolean c){
        isConfirmed = c;
    }
    public boolean isConfirmed(){
        return isConfirmed;
    }
}
