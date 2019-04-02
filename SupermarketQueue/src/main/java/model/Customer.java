//Created by Farcas Alexandru
//UTCN 2019
//23/03/2019
package model;

public class Customer {
    private int timeOfArrival;
    private int serviceDuration;
    private int waitingTime;

    public Customer(int timeOfArrival, int serviceDuration) {
        this.timeOfArrival = timeOfArrival;
        this.serviceDuration = serviceDuration;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getServiceDuration() {
        return serviceDuration;
    }
}
