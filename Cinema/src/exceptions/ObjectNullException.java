package exceptions;

/**
 *Exception caso seja inserido letra em vez de numero
*/
public class ObjectNullException extends Exception {
    @Override
    public String getMessage(){
            return "Objeto Null";
    }
    
}