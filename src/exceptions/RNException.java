package exceptions;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class RNException extends Exception{
    public RNException(String s) {
        JOptionPane.showMessageDialog(null, s, "Aviso", WARNING_MESSAGE);
    }

    public RNException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RNException(Throwable throwable) {
        super(throwable);
    }
    
    
}
