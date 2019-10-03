//Created by Farcas Alexandru
//UTCN 2019
//27/04/2019
package businessLayer;

import javafx.application.Platform;
import javafx.scene.control.TextField;

public class ErrorMessage {
    public void display(TextField error, String message) {

        final Runnable show = new Runnable() {
            @Override
            public void run() {
                try {
                    error.setText(message);
                    error.setVisible(true);
                } catch(final Exception e) {
                    e.printStackTrace();
                }
            }
        };

        final Runnable hide = new Runnable() {

            @Override
            public void run() {
                try {
                    error.setVisible(false);
                } catch(final Exception e) {
                    e.printStackTrace();
                }
            }
        };

        final Thread thread = new Thread(() -> {
            try {
                Platform.runLater(show);
                Thread.sleep(3000);
                Platform.runLater(hide);
            } catch(final Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
