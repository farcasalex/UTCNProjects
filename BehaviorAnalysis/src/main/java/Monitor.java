//Created by Farcas Alexandru
//UTCN 2019
//26/05/2019

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Monitor {
    private List<MonitoredData> monitoredData = new ArrayList<>();

    public Monitor(String fileName) {
        Stream<String> stream = null;

        try {
            stream = Files.lines(Paths.get(fileName));
            stream
                    .map(l -> l.split("\t\t"))
                    .forEach(a -> createMonitoredData(a[0], a[1], a[2]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createMonitoredData(String startTime, String endTime, String activityLabel) {
        try {
            MonitoredData newData = new MonitoredData(startTime, endTime, activityLabel);
            monitoredData.add(newData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int monitoredDays(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        int days = 0;
        try {
            Date startDate = format.parse(monitoredData.get(0).getStartTime());
            Date endDate = format.parse(monitoredData.get(monitoredData.size() - 1).getStartTime());
            long difference = Math.abs(startDate.getTime() - endDate.getTime());
            days = (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS) + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    public HashMap<String, Integer> activitiesCount(){
        HashMap<String, Integer> result = new HashMap<>();
        this.monitoredData.forEach(data -> {
            if (result.containsKey(data.getActivityLabel())){
                Integer count = result.get(data.getActivityLabel());
                result.replace(data.getActivityLabel(), count, count + 1);
            }else{
                result.put(data.getActivityLabel(), 1);
            }
        });
        return result;
    }

    public String activityDuration(){
        final String[] result = {""};
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        this.monitoredData.forEach(data -> {
            try {
                int difference = 0;
                Date startTime = format.parse(data.getStartTime());
                Date endTime = format.parse(data.getEndTime());
                long diffInMilliseconds = Math.abs(startTime.getTime() - endTime.getTime());
                difference = (int) TimeUnit.MINUTES.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
                if (difference == 0){
                    difference = (int) TimeUnit.SECONDS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
                    result[0] += data.getActivityLabel() + ": " + difference + " seconds\n";
                }
                else{
                    result[0] += data.getActivityLabel() + ": " + difference + " minutes\n";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return result[0];
    }

    private int getDuration(String start, String end){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        int difference = 0;
        try {
            Date startTime = format.parse(start);
            Date endTime = format.parse(end);
            long diffInMilliseconds = Math.abs(startTime.getTime() - endTime.getTime());
            difference = (int) TimeUnit.MINUTES.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return difference;
    }

    public HashMap<String, Integer> activitiesTotalDuration(){
        HashMap<String, Integer> result = new HashMap<>();
        this.monitoredData.forEach(data -> {
            int duration  = this.getDuration(data.getStartTime(), data.getEndTime());
            if (result.containsKey(data.getActivityLabel())){
                Integer count = result.get(data.getActivityLabel());
                result.replace(data.getActivityLabel(), count, count + duration);
            }else{
                result.put(data.getActivityLabel(), duration);
            }
        });
        return result;
    }

    private boolean durationCheck(MonitoredData data){
        if (this.getDuration(data.getStartTime(), data.getEndTime()) < 5){
            return true;
        }
        return false;
    }

    private HashMap<String, Integer> validCheck(){
        HashMap<String, Integer> result = new HashMap<>();
        this.monitoredData.forEach(data -> {
            if (result.containsKey(data.getActivityLabel())){
                Integer count = result.get(data.getActivityLabel());
                if (durationCheck(data)){
                    result.replace(data.getActivityLabel(), count, count + 1);
                }
            }else{
                if (durationCheck(data)){
                    result.put(data.getActivityLabel(), 1);
                }
            }
        });
        return result;
    }

    public String filter(){
        HashMap<String, Integer> total = this.activitiesCount();
        HashMap<String, Integer> good = this.validCheck();

        final String[] result = {""};
        good.forEach((s, integer) -> {
            try {
                int totalCount = total.get(s);
                int validCount = integer;
                if (validCount * 100 / totalCount > 90){
                    result[0] += s + "\n";
                }
            } catch (Exception e) { }
        });
        return result[0];
    }

    @Override
    public String toString(){
        String result = "";
        for (MonitoredData monitoredData : this.monitoredData){
            result += monitoredData.toString() + "\n";
        }
        return result;
    }
}
