package exceptions;

public class RNException extends Exception{
    public RNException(String s) {
        super(s);
    }

    public RNException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RNException(Throwable throwable) {
        super(throwable);
    }
}
