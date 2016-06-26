package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.F1;
import static javafx.scene.input.KeyCode.F10;
import static javafx.scene.input.KeyCode.F11;
import static javafx.scene.input.KeyCode.F12;
import static javafx.scene.input.KeyCode.F2;
import static javafx.scene.input.KeyCode.F3;
import static javafx.scene.input.KeyCode.F4;
import static javafx.scene.input.KeyCode.F5;
import static javafx.scene.input.KeyCode.F6;
import static javafx.scene.input.KeyCode.F7;
import static javafx.scene.input.KeyCode.F8;
import static javafx.scene.input.KeyCode.F9;
import javafx.scene.input.KeyEvent;

public abstract class MaskFieldUtil {

    private static List<KeyCode> ignoreKeyCodes;

    static {
        ignoreKeyCodes = new ArrayList<>();
        Collections.addAll(ignoreKeyCodes, new KeyCode[]{F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12});
    }

    public static void ignoreKeys(final TextField textField) {
        textField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (ignoreKeyCodes.contains(keyEvent.getCode())) {
                    keyEvent.consume();
                }
            }
        });
    }

    /**
     * Monta a mascara para Hora (HH:MM).
     *
     * @param textField TextField
     */
    public static void timeField(final TextField textField) {
        maxField(textField, 5);

        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() < 5) {
                    String value = textField.getText();
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("(\\d{2})(\\d)", "$1:$2");
                    textField.setText(value);
                    positionCaret(textField);
                }
            }
        });
    }

    /**
     * Campo que aceita somente numericos.
     *
     * @param textField TextField
     */
    public static void numericField(final TextField textField) {
        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = textField.getText().charAt(oldValue.intValue());
                    if (!(ch >= '0' && ch <= '9')) {
                        textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                    }
                }
            }
        });
    }


    /**
     * Devido ao incremento dos caracteres das mascaras eh necessario que o cursor sempre se posicione no final da string.
     *
     * @param textField TextField
     */
    private static void positionCaret(final TextField textField) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Posiciona o cursor sempre a direita.
                textField.positionCaret(textField.getText().length());
            }
        });
    }

    /**
     * @param textField TextField.
     * @param length    Tamanho do campo.
     */
    private static void maxField(final TextField textField, final Integer length) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue.length() > length)
                    textField.setText(oldValue);
            }
        });
    }

}