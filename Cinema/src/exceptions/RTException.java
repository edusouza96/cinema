package exceptions;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

/**
 *
 * @author eduardo
 */
public class RTException extends Exception{
    public RTException(String s) {
        JOptionPane.showMessageDialog(null, s, "Aviso - Programa Encerrado", WARNING_MESSAGE);
        System.exit(0);
    }
}
