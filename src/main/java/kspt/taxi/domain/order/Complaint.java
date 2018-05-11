package kspt.taxi.domain.order;

public class Complaint {
    private String passengerText;
    private String driverText;
    private boolean confirmed;

    public Complaint(String passengerText, boolean confirmed) {
        this.passengerText = passengerText;
        this.confirmed = false;
    }

    public String getPassengerText() {
        return passengerText;
    }

    public String getDriverText() {
        return driverText;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setPassengerText(String passengerText) {
        this.passengerText = passengerText;
    }

    public void setDriverText(String driverText) {
        this.driverText = driverText;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
