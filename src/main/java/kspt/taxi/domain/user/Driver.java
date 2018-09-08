package kspt.taxi.domain.user;

import com.sun.org.apache.xpath.internal.operations.Or;
import kspt.taxi.domain.order.Order;
import kspt.taxi.exceptions.NoRightsException;
import kspt.taxi.exceptions.NotAuthenticatedException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@ToString(exclude = "id")
@EqualsAndHashCode(exclude = "id")
@Log
public class Driver extends User {
    public static final int MIN_SCORE = 1;
    public static final int MAX_SCORE = 5;

    @Getter
    private int id;

    @Getter
    @Setter
    private boolean busy;

    @Getter
    private float rating;

    private int sumScore = 0;

    private int votersCount = 0;

    public Driver(String login_, String name_, String email_, String phone_) {
        super(login_, name_, email_, phone_);
    }

    public Driver(User user) {
        super(user);
    }

    public boolean acceptOrder(Order order) throws NotAuthenticatedException, NoRightsException {
        checkAuthenticated();
        checkAccessRights(order);
        setBusy(true);
        return order.confirmDriver();
    }

    public boolean declineOrder(Order order) throws NotAuthenticatedException, NoRightsException {
        checkAuthenticated();
        checkAccessRights(order);
        setBusy(false);
        return order.rejectDriver();
    }

    public void setWaitingTime(Order order, int minutes) throws NotAuthenticatedException, NoRightsException {
        checkAuthenticated();
        checkAccessRights(order);
        if (minutes >= 0)
            order.getCostCalculation().setWaitingTime(minutes);
    }

    public boolean closeOrder(Order order) throws NotAuthenticatedException, NoRightsException {
        checkAuthenticated();
        checkAccessRights(order);
        return order.completeOrder();
    }

    public void updateRating(int score) {
        sumScore += score;
        votersCount += 1;
        rating = sumScore / votersCount;
    }

    public void addCommentToRecall(Order order, String comment) throws NotAuthenticatedException, NoRightsException {
        checkAuthenticated();
        checkAccessRights(order);
        if (!comment.isEmpty())
            order.getRecall().setDriverText(comment);
    }

    private void checkAccessRights(Order order) throws NoRightsException {
        if (order.getDriver() != this)
            throw new NoRightsException("User " + getLogin() + "cannot change order " + order.getId());
    }
}
