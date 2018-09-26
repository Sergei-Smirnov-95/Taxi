package Exceptions;

public class HaveNotUserEx extends Exception {
    public HaveNotUserEx(){
        super("Have not user with this login");
    }
}
