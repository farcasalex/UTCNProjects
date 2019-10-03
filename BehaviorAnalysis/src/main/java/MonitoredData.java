//Created by Farcas Alexandru
//UTCN 2019
//24/05/2019

public class MonitoredData {
    private String startTime;
    private String endTime;
    private String activityLabel;

    public MonitoredData(String startTime, String endTime, String activityLabel) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityLabel = activityLabel;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getActivityLabel() {
        return activityLabel;
    }

    public void setActivityLabel(String activityLabel) {
        this.activityLabel = activityLabel;
    }

    @Override
    public String toString(){
        return this.getStartTime() + "\t\t" + this.getEndTime() + "\t\t" + this.getActivityLabel();
    }
}
