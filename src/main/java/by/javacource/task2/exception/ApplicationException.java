package by.javacource.task2.exception;

public class ApplicationException extends Exception{

    public ApplicationException() {
    }


    public ApplicationException(String message) {
        super(message);
    }


    public ApplicationException(Exception e) {
        super(e);
    }


    public ApplicationException(String message, Exception e) {
        super(message, e);
    }

}
