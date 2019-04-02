//Created by Farcas Alexandru
//UTCN 2019
//29/03/2019
package thread;

import model.Customer;
import model.CustomerQueue;

import java.util.concurrent.atomic.AtomicReference;

public class QueueThread extends Thread implements Runnable{

    private CustomerQueue queue;
    private boolean stop = false;
    private AtomicReference<String> logView;

    public void stopThread(){
        this.stop = true;
    }

    public QueueThread(CustomerQueue queue, AtomicReference<String> logView){
        this.queue = queue;
        this.logView = logView;
    }

    @Override
    public void run(){
        while (stop != true){
            if (queue.getQueue().peek() != null){
                Customer customer = queue.getQueue().peek();
                try {
                    this.sleep(1000 * customer.getServiceDuration());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.getQueue().remove();
                queue.updateTotalServiceTime();
                logView.set(logView.get() + " [finished processing a customer] ");
            }
        }
        this.stop = false;
    }
}
