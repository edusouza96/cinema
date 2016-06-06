package exceptions;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

/**
 *Exception caso seja inserido letra em vez de numero
*/
public class HourNotAvailable extends Exception {
    public HourNotAvailable() {
        JOptionPane.showMessageDialog(null, "Já existe uma sessão neste horario", "Aviso", WARNING_MESSAGE);
  
    }
    
}