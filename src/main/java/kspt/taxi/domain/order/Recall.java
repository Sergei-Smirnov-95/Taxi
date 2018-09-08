package kspt.taxi.domain.order;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@ToString(exclude = "id")
@EqualsAndHashCode(exclude = "id")
@Log
public class Recall {

    @Getter
    private int id;

    @Getter
    @Setter
    private String passengerText;

    @Getter
    @Setter
    private int passengerScore;

    @Getter
    @Setter
    private String driverText;

    @Getter
    @Setter
    private boolean confirmed;

    public Recall(String passengerText, int passengerScore) {
        this.passengerText = passengerText;
        this.passengerScore = passengerScore;
        this.confirmed = false;
    }
}
