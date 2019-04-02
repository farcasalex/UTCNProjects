//Created by Farcas Alexandru
//UTCN 2019
//30/03/2019
package thread;

import javafx.scene.control.Button;

public class ButtonDisableThread extends Thread implements Runnable {

    private Button start, abort;
    private int timer = 0;
    private int duration;
    private boolean stop = false;

    public ButtonDisableThread(Button start, Button abort, int duration){
        this.start = start;
        this.abort = abort;
        this.duration = duration;
    }

    public ButtonDisableThread(){

    }

    public void stopThread() {
        this.stop = true;
    }

    @Override
    public void run(){
        while (stop != true && timer < duration){
            start.setDisable(true);
            abort.setDisable(false);
            try {
                this.sleep(1000);
                timer++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        start.setDisable(false);
        abort.setDisable(true);
        this.stop = false;
    }
}
