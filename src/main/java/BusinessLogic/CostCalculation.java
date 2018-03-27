package BusinessLogic;

public class CostCalculation {
    private float routeLength;
    private float waitingTime;
    private float totalCost;

    public boolean setRouteLength(float routeLength) {
        if(routeLength > 0) {
            this.routeLength = routeLength;
            return true;
        }
        return false;
    }

    public boolean setWaitingTime(float waitingTime) {
        if (waitingTime > 0) {
            this.waitingTime = waitingTime;
            return true;
        }
        return false;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = routeLength * Tariff.costPerKm +
                ((waitingTime > Tariff.freeMinutes) ? (waitingTime - Tariff.freeMinutes) * Tariff.costPerMin : 0);
    }

    public float getRouteLength() {
        return routeLength;
    }

    public float getWaitingTime() {
        return waitingTime;
    }

    public float getTotalCost() {
        return totalCost;
    }
}
