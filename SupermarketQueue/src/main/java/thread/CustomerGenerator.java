//Created by Farcas Alexandru
//UTCN 2019
//29/03/2019
package thread;

import model.Customer;
import model.CustomerQueue;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class CustomerGenerator extends Thread implements Runnable {

    private ArrayList<CustomerQueue> queues;
    private int time;
    private int minArrival;
    private int maxArrival;
    private int minService;
    private int maxService;
    private boolean stop = false;
    private AtomicReference<String> logView;

    public CustomerGenerator(ArrayList<CustomerQueue> queues, int minArrival, int maxArrival, int minService, int maxService, AtomicReference<String> logView){
        this.queues = queues;
        this.minArrival = minArrival;
        this.maxArrival = maxArrival;
        this.minService = minService;
        this.maxService = maxService;
        this.logView = logView;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void stopSimulation(){
        this.stop = true;
    }

    public void sendToQueue(Customer customer){
        CustomerQueue sendTo = new CustomerQueue();
        int minService = Integer.MAX_VALUE;

        for(CustomerQueue i: this.queues){
            if (i.getTotalServiceTime() < minService) {
                minService = i.getTotalServiceTime();
                sendTo = i;
            }
        }
        customer.setWaitingTime(minService);
        sendTo.getQueue().add(customer);
        sendTo.updateTotalServiceTime();
        sendTo.updateAverageData(customer);
    }

    @Override
    public void run(){
        Random rand = new Random();
        try {
            this.sleep(1000 * (rand.nextInt((maxArrival - minArrival) + 1) + minArrival));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (stop != true){
            int serviceDouration = rand.nextInt((maxService - minService) + 1) + minService;
            int arrivalTime = rand.nextInt((maxArrival - minArrival) + 1) + minArrival;
            this.sendToQueue(new Customer(time, serviceDouration));
            logView.set(logView.get() + "[new customer -> service time = " + serviceDouration + "] ");
            try {
                this.sleep(1000 * arrivalTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.stop = false;
    }
}
