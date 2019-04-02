//UTCN 2019
//23/03/2019
package thread;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class ErrorMessage {
    public void display(Label error) {

        final Runnable show = new Runnable() {
            @Override
            public void run() {
                try {
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
