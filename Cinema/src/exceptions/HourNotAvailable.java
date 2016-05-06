package exceptions;

/**
 *Exception caso seja inserido letra em vez de numero
*/
public class HourNotAvailable extends RuntimeException {
    public HourNotAvailable() {
        super("Já existe uma sessão neste horario");
    }
    
}