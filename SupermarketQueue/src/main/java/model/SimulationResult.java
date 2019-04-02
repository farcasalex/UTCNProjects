//Created by Farcas Alexandru
//UTCN 2019
//31/03/2019
package model;

public class SimulationResult {
    private float averageWaitingTime, averageServiceTime = 0;
    private int emptyTime, maxServiceTime, peekHour = 0;

    public float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public void setAverageWaitingTime(float averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public float getAverageServiceTime() {
        return averageServiceTime;
    }

    public void setAverageServiceTime(float averageServiceTime) {
        this.averageServiceTime = averageServiceTime;
    }

    public int getEmptyTime() {
        return emptyTime;
    }

    public void setEmptyTime(int emptyTime) {
        this.emptyTime = emptyTime;
    }

    public int getPeekHour() {
        return peekHour;
    }

    public void setPeekHour(int peekHour) {
        this.peekHour = peekHour;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    public void setMaxServiceTime(int maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }
}
