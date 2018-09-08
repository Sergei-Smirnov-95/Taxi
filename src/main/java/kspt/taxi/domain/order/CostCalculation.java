package kspt.taxi.domain.order;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@Log
public class CostCalculation {

    static public final float COST_PER_KM = 10;
    static public final float COST_PER_MIN = 5;
    static public final int FREE_MINUTES = 10;

    @Getter
    private int id;

    @Getter
    @Setter
    private float routeLength;

    @Getter
    @Setter
    private float waitingTime;

    private float totalCost;

    public CostCalculation() {
    }

    public void getTotalCost() {
        totalCost = routeLength * COST_PER_KM +
                ((waitingTime > FREE_MINUTES) ? (waitingTime - FREE_MINUTES) * COST_PER_MIN : 0);
    }
}
