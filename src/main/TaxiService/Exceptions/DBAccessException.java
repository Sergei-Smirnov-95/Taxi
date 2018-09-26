package Exceptions;


public class DBAccessException extends Exception{
    public DBAccessException(){
        super("Access denied");
    }
}
