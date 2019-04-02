//Created by Farcas Alexandru
//UTCN 2019
//26/03/2019
package model;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CustomerQueue {

    private ConcurrentLinkedQueue<Customer> queue = new ConcurrentLinkedQueue<>();
    private int numberOfProcessedCustomers = 0;
    private int totalServiceTime = 0;
    private float averageWaitingTime = 0;
    private float averageServiceTime = 0;

    public CustomerQueue() {

    }

    public ConcurrentLinkedQueue<Customer> getQueue() {
        return queue;
    }

    public int getTotalServiceTime() {
        return totalServiceTime;
    }

    public float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public float getAverageServiceTime() {
        return averageServiceTime;
    }

    public void updateAverageData(Customer customer){
        // recalculate the average waiting time
        this.averageWaitingTime = (this.averageWaitingTime * numberOfProcessedCustomers + customer.getWaitingTime()) / (++numberOfProcessedCustomers);

        // recalculate the average service time
        this.averageServiceTime = (this.averageServiceTime * numberOfProcessedCustomers + customer.getServiceDuration()) / (++numberOfProcessedCustomers);
    }

    public void updateTotalServiceTime(){
        // recalculate the total service time
        int serviceTime = 0;
        for(Customer i : this.queue)
            serviceTime += i.getServiceDuration();
        this.totalServiceTime = serviceTime;
    }
}
