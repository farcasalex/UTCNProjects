//Created by Farcas Alexandru
//UTCN 2019
//29/03/2019
package thread;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import model.CustomerQueue;
import model.SimulationResult;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Simulation  extends Thread implements Runnable{
    private int time = 0;
    private int minArrival, maxArrival, minService, maxService, simulationDuration, numberOfQueues,
                firstQueueSize, secondQueueSize, thirdQueueSize, fourthQueueSize, fifthQueueSize;
    private Button[] firstQueue, secondQueue, thirdQueue, fourthQueue, fifthQueue;
    private boolean stop = false;
    private ArrayList<CustomerQueue> queues = new ArrayList<>();
    private TextArea logView;
    private AtomicReference<String> logViewString = new AtomicReference<>();
    private GridPane resultGrid;
    private SimulationResult firstQueueResult = new SimulationResult();
    private SimulationResult secondQueueResult = new SimulationResult();
    private SimulationResult thirdQueueResult = new SimulationResult();
    private SimulationResult fourthQueueResult = new SimulationResult();
    private SimulationResult fifthQueueResult = new SimulationResult();

    public Simulation(int minArrival, int maxArrival, int minService, int maxService, int numberOfQueues, int simulationDuration,
                      Button[] firstQueue, Button[] secondQueue, Button[] thirdQueue, Button[] fourthQueue, Button[] fifthQueue, TextArea logView,
                      SimulationResult firstQueueResult, SimulationResult secondQueueResult, SimulationResult thirdQueueResult,
                      SimulationResult fourthQueueResult, SimulationResult fifthQueueResult, GridPane resultGrid){
        this.minArrival = minArrival;
        this.maxArrival = maxArrival;
        this.minService = minService;
        this.maxService = maxService;
        this.simulationDuration =  simulationDuration;
        this.numberOfQueues = numberOfQueues;
        this.firstQueue = firstQueue;
        this.secondQueue = secondQueue;
        this.thirdQueue = thirdQueue;
        this.fourthQueue = fourthQueue;
        this.fifthQueue = fifthQueue;
        this.logView = logView;
        this.logViewString.set(logView.getText());
        this.firstQueueResult = firstQueueResult;
        this.secondQueueResult = secondQueueResult;
        this.thirdQueueResult = thirdQueueResult;
        this.fourthQueueResult = fourthQueueResult;
        this.fifthQueueResult = fifthQueueResult;
        this.resultGrid = resultGrid;
        for (int i = 0; i < numberOfQueues; i++){
            CustomerQueue queue = new CustomerQueue();
            this.queues.add(queue);
        }
    }

    public Simulation() { }

    public void updateQueues(){
        switch (numberOfQueues) {
            case 1: {
                firstQueueSize = 0;
                secondQueueSize = 0;
                thirdQueueSize = queues.get(0).getQueue().size();
                fourthQueueSize = 0;
                fifthQueueSize = 0;
                break;
            }
            case 2: {
                firstQueueSize = 0;
                secondQueueSize = queues.get(0).getQueue().size();
                thirdQueueSize = 0;
                fourthQueueSize = queues.get(1).getQueue().size();
                fifthQueueSize = 0;
                break;
            }
            case 3: {
                firstQueueSize = queues.get(0).getQueue().size();
                secondQueueSize = queues.get(1).getQueue().size();
                thirdQueueSize = queues.get(2).getQueue().size();
                fourthQueueSize = 0;
                fifthQueueSize = 0;
                break;
            }
            case 4: {
                firstQueueSize = queues.get(0).getQueue().size();
                secondQueueSize = queues.get(1).getQueue().size();
                thirdQueueSize = queues.get(2).getQueue().size();
                fourthQueueSize = queues.get(3).getQueue().size();
                fifthQueueSize = 0;
                break;
            }
            case 5: {
                firstQueueSize = queues.get(0).getQueue().size();
                secondQueueSize = queues.get(1).getQueue().size();
                thirdQueueSize = queues.get(2).getQueue().size();
                fourthQueueSize = queues.get(3).getQueue().size();
                fifthQueueSize = queues.get(4).getQueue().size();
                break;
            }
            default:
                break;
        }
    }

    public void stopSimulation(){
        this.stop = true;
    }

    public TextArea getLogView() {
        return logView;
    }

    public ArrayList<CustomerQueue> getQueues() {
        return queues;
    }

    @Override
    public void run(){
        switch (numberOfQueues){
            case 1: {
                if (queues.get(0).getQueue().isEmpty() == true)
                    thirdQueueResult.setEmptyTime(thirdQueueResult.getEmptyTime() + 1);
                firstQueueSize = 0;
                secondQueueSize = 0;
                thirdQueueSize = queues.get(0).getQueue().size();
                fourthQueueSize = 0;
                fifthQueueSize = 0;
                break;
            }
            case 2: {
                if (queues.get(0).getQueue().isEmpty() == true)
                    secondQueueResult.setEmptyTime(secondQueueResult.getEmptyTime() + 1);
                if (queues.get(1).getQueue().isEmpty() == true)
                    fourthQueueResult.setEmptyTime(fifthQueueResult.getEmptyTime() + 1);
                firstQueueSize = 0;
                secondQueueSize = queues.get(0).getQueue().size();
                thirdQueueSize = 0;
                fourthQueueSize = queues.get(1).getQueue().size();
                fifthQueueSize = 0;
                break;
            }
            case 3: {
                if (queues.get(0).getQueue().isEmpty() == true)
                    firstQueueResult.setEmptyTime(firstQueueResult.getEmptyTime() + 1);
                if (queues.get(1).getQueue().isEmpty() == true)
                    secondQueueResult.setEmptyTime(secondQueueResult.getEmptyTime() + 1);
                if (queues.get(2).getQueue().isEmpty() == true)
                    thirdQueueResult.setEmptyTime(thirdQueueResult.getEmptyTime() + 1);
                firstQueueSize = queues.get(0).getQueue().size();
                secondQueueSize = queues.get(1).getQueue().size();
                thirdQueueSize = queues.get(2).getQueue().size();
                fourthQueueSize = 0;
                fifthQueueSize = 0;
                break;
            }
            case 4: {
                if (queues.get(0).getQueue().isEmpty() == true)
                    firstQueueResult.setEmptyTime(firstQueueResult.getEmptyTime() + 1);
                if (queues.get(1).getQueue().isEmpty() == true)
                    secondQueueResult.setEmptyTime(secondQueueResult.getEmptyTime() + 1);
                if (queues.get(2).getQueue().isEmpty() == true)
                    thirdQueueResult.setEmptyTime(thirdQueueResult.getEmptyTime() + 1);
                if (queues.get(3).getQueue().isEmpty() == true)
                    firstQueueResult.setEmptyTime(firstQueueResult.getEmptyTime() + 1);
                firstQueueSize = queues.get(0).getQueue().size();
                secondQueueSize = queues.get(1).getQueue().size();
                thirdQueueSize = queues.get(2).getQueue().size();
                fourthQueueSize = queues.get(3).getQueue().size();
                fifthQueueSize = 0;
                break;
            }
            case 5: {
                if (queues.get(0).getQueue().isEmpty() == true)
                    firstQueueResult.setEmptyTime(firstQueueResult.getEmptyTime() + 1);
                if (queues.get(1).getQueue().isEmpty() == true)
                    secondQueueResult.setEmptyTime(secondQueueResult.getEmptyTime() + 1);
                if (queues.get(2).getQueue().isEmpty() == true)
                    thirdQueueResult.setEmptyTime(thirdQueueResult.getEmptyTime() + 1);
                if (queues.get(3).getQueue().isEmpty() == true)
                    firstQueueResult.setEmptyTime(firstQueueResult.getEmptyTime() + 1);
                if (queues.get(4).getQueue().isEmpty() == true)
                    fifthQueueResult.setEmptyTime(fifthQueueResult.getEmptyTime() + 1);
                firstQueueSize = queues.get(0).getQueue().size();
                secondQueueSize = queues.get(1).getQueue().size();
                thirdQueueSize = queues.get(2).getQueue().size();
                fourthQueueSize = queues.get(3).getQueue().size();
                fifthQueueSize = queues.get(4).getQueue().size();
                break;
            }
            default:break;
        }
        VisualThread visual = new VisualThread(numberOfQueues, firstQueue, firstQueueSize, secondQueue, secondQueueSize, thirdQueue, thirdQueueSize, fourthQueue, fourthQueueSize, fifthQueue, fifthQueueSize);
        visual.start();
        CustomerGenerator generator = new CustomerGenerator(this.queues, this.minArrival, this.maxArrival, this.minService, this.maxService, this.logViewString);
        generator.start();
        ArrayList<QueueThread> threads = new ArrayList<>();
        for (CustomerQueue i : this.queues)
            threads.add(new QueueThread(i, logViewString));
        for (QueueThread i : threads)
            i.start();
        logView.setVisible(true);
        resultGrid.setVisible(false);
        while(stop != true && time < simulationDuration) {
            try{
                switch (numberOfQueues){
                    case 1: {
                        if (queues.get(0).getQueue().isEmpty() == true)
                            thirdQueueResult.setEmptyTime(thirdQueueResult.getEmptyTime() + 1);
                        if (queues.get(0).getTotalServiceTime() > thirdQueueResult.getMaxServiceTime()){
                            thirdQueueResult.setMaxServiceTime(queues.get(0).getTotalServiceTime());
                            thirdQueueResult.setPeekHour(time);
                        }
                        break;
                    }
                    case 2: {
                        if (queues.get(0).getQueue().isEmpty() == true)
                            secondQueueResult.setEmptyTime(secondQueueResult.getEmptyTime() + 1);
                        if (queues.get(1).getQueue().isEmpty() == true)
                            fourthQueueResult.setEmptyTime(fifthQueueResult.getEmptyTime() + 1);
                        if (queues.get(0).getTotalServiceTime() > secondQueueResult.getMaxServiceTime()){
                            secondQueueResult.setMaxServiceTime(queues.get(0).getTotalServiceTime());
                            secondQueueResult.setPeekHour(time);
                        }
                        if (queues.get(1).getTotalServiceTime() > fourthQueueResult.getMaxServiceTime()){
                            fourthQueueResult.setMaxServiceTime(queues.get(1).getTotalServiceTime());
                            fourthQueueResult.setPeekHour(time);
                        }
                        break;
                    }
                    case 3: {
                        if (queues.get(0).getQueue().isEmpty() == true)
                            firstQueueResult.setEmptyTime(firstQueueResult.getEmptyTime() + 1);
                        if (queues.get(1).getQueue().isEmpty() == true)
                            secondQueueResult.setEmptyTime(secondQueueResult.getEmptyTime() + 1);
                        if (queues.get(2).getQueue().isEmpty() == true)
                            thirdQueueResult.setEmptyTime(thirdQueueResult.getEmptyTime() + 1);
                        if (queues.get(0).getTotalServiceTime() > firstQueueResult.getMaxServiceTime()){
                            firstQueueResult.setMaxServiceTime(queues.get(0).getTotalServiceTime());
                            firstQueueResult.setPeekHour(time);
                        }
                        if (queues.get(1).getTotalServiceTime() > secondQueueResult.getMaxServiceTime()){
                            secondQueueResult.setMaxServiceTime(queues.get(1).getTotalServiceTime());
                            secondQueueResult.setPeekHour(time);
                        }
                        if (queues.get(2).getTotalServiceTime() > thirdQueueResult.getMaxServiceTime()){
                            thirdQueueResult.setMaxServiceTime(queues.get(2).getTotalServiceTime());
                            thirdQueueResult.setPeekHour(time);
                        }
                        break;
                    }
                    case 4: {
                        if (queues.get(0).getQueue().isEmpty() == true)
                            firstQueueResult.setEmptyTime(firstQueueResult.getEmptyTime() + 1);
                        if (queues.get(1).getQueue().isEmpty() == true)
                            secondQueueResult.setEmptyTime(secondQueueResult.getEmptyTime() + 1);
                        if (queues.get(2).getQueue().isEmpty() == true)
                            thirdQueueResult.setEmptyTime(thirdQueueResult.getEmptyTime() + 1);
                        if (queues.get(3).getQueue().isEmpty() == true)
                            fourthQueueResult.setEmptyTime(fourthQueueResult.getEmptyTime() + 1);
                        if (queues.get(0).getTotalServiceTime() > firstQueueResult.getMaxServiceTime()){
                            firstQueueResult.setMaxServiceTime(queues.get(0).getTotalServiceTime());
                            firstQueueResult.setPeekHour(time);
                        }
                        if (queues.get(1).getTotalServiceTime() > secondQueueResult.getMaxServiceTime()){
                            secondQueueResult.setMaxServiceTime(queues.get(1).getTotalServiceTime());
                            secondQueueResult.setPeekHour(time);
                        }
                        if (queues.get(2).getTotalServiceTime() > thirdQueueResult.getMaxServiceTime()){
                            thirdQueueResult.setMaxServiceTime(queues.get(2).getTotalServiceTime());
                            thirdQueueResult.setPeekHour(time);
                        }
                        if (queues.get(3).getTotalServiceTime() > fourthQueueResult.getMaxServiceTime()){
                            fourthQueueResult.setMaxServiceTime(queues.get(3).getTotalServiceTime());
                            fourthQueueResult.setPeekHour(time);
                        }
                        break;
                    }
                    case 5: {
                        if (queues.get(0).getQueue().isEmpty() == true)
                            firstQueueResult.setEmptyTime(firstQueueResult.getEmptyTime() + 1);
                        if (queues.get(1).getQueue().isEmpty() == true)
                            secondQueueResult.setEmptyTime(secondQueueResult.getEmptyTime() + 1);
                        if (queues.get(2).getQueue().isEmpty() == true)
                            thirdQueueResult.setEmptyTime(thirdQueueResult.getEmptyTime() + 1);
                        if (queues.get(3).getQueue().isEmpty() == true)
                            fourthQueueResult.setEmptyTime(fourthQueueResult.getEmptyTime() + 1);
                        if (queues.get(4).getQueue().isEmpty() == true)
                            fifthQueueResult.setEmptyTime(fifthQueueResult.getEmptyTime() + 1);
                        if (queues.get(0).getTotalServiceTime() > firstQueueResult.getMaxServiceTime()){
                            firstQueueResult.setMaxServiceTime(queues.get(0).getTotalServiceTime());
                            firstQueueResult.setPeekHour(time);
                        }
                        if (queues.get(1).getTotalServiceTime() > secondQueueResult.getMaxServiceTime()){
                            secondQueueResult.setMaxServiceTime(queues.get(1).getTotalServiceTime());
                            secondQueueResult.setPeekHour(time);
                        }
                        if (queues.get(2).getTotalServiceTime() > thirdQueueResult.getMaxServiceTime()){
                            thirdQueueResult.setMaxServiceTime(queues.get(2).getTotalServiceTime());
                            thirdQueueResult.setPeekHour(time);
                        }
                        if (queues.get(3).getTotalServiceTime() > fourthQueueResult.getMaxServiceTime()){
                            fourthQueueResult.setMaxServiceTime(queues.get(3).getTotalServiceTime());
                            fourthQueueResult.setPeekHour(time);
                        }
                        if (queues.get(4).getTotalServiceTime() > fifthQueueResult.getMaxServiceTime()){
                            fifthQueueResult.setMaxServiceTime(queues.get(4).getTotalServiceTime());
                            fifthQueueResult.setPeekHour(time);
                        }
                        break;
                    }
                    default:break;
                }
                logViewString.set(logViewString.get() + "\n" + time + " : ");
                logView.setText(logViewString.get());
                logView.setScrollTop(Double.MAX_VALUE);
                generator.setTime(this.time);
                this.updateQueues();
                visual.update(firstQueueSize, secondQueueSize, thirdQueueSize, fourthQueueSize, fifthQueueSize);
                this.sleep(1000);
                time++;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        switch (numberOfQueues){
            case 1: {
                thirdQueueResult.setAverageServiceTime(queues.get(0).getAverageServiceTime());
                thirdQueueResult.setAverageWaitingTime(queues.get(0).getAverageWaitingTime());
                break;
            }
            case 2: {
                secondQueueResult.setAverageServiceTime(queues.get(0).getAverageServiceTime());
                secondQueueResult.setAverageWaitingTime(queues.get(0).getAverageWaitingTime());
                fourthQueueResult.setAverageServiceTime(queues.get(1).getAverageServiceTime());
                fourthQueueResult.setAverageWaitingTime(queues.get(1).getAverageWaitingTime());
                break;
            }
            case 3: {
                firstQueueResult.setAverageServiceTime(queues.get(0).getAverageServiceTime());
                firstQueueResult.setAverageWaitingTime(queues.get(0).getAverageWaitingTime());
                secondQueueResult.setAverageServiceTime(queues.get(1).getAverageServiceTime());
                secondQueueResult.setAverageWaitingTime(queues.get(1).getAverageWaitingTime());
                thirdQueueResult.setAverageServiceTime(queues.get(2).getAverageServiceTime());
                thirdQueueResult.setAverageWaitingTime(queues.get(2).getAverageWaitingTime());
                break;
            }
            case 4: {
                firstQueueResult.setAverageServiceTime(queues.get(0).getAverageServiceTime());
                firstQueueResult.setAverageWaitingTime(queues.get(0).getAverageWaitingTime());
                secondQueueResult.setAverageServiceTime(queues.get(1).getAverageServiceTime());
                secondQueueResult.setAverageWaitingTime(queues.get(1).getAverageWaitingTime());
                thirdQueueResult.setAverageServiceTime(queues.get(2).getAverageServiceTime());
                thirdQueueResult.setAverageWaitingTime(queues.get(2).getAverageWaitingTime());
                fourthQueueResult.setAverageServiceTime(queues.get(3).getAverageServiceTime());
                fourthQueueResult.setAverageWaitingTime(queues.get(3).getAverageWaitingTime());
                break;
            }
            case 5: {
                firstQueueResult.setAverageServiceTime(queues.get(0).getAverageServiceTime());
                firstQueueResult.setAverageWaitingTime(queues.get(0).getAverageWaitingTime());
                secondQueueResult.setAverageServiceTime(queues.get(1).getAverageServiceTime());
                secondQueueResult.setAverageWaitingTime(queues.get(1).getAverageWaitingTime());
                thirdQueueResult.setAverageServiceTime(queues.get(2).getAverageServiceTime());
                thirdQueueResult.setAverageWaitingTime(queues.get(2).getAverageWaitingTime());
                fourthQueueResult.setAverageServiceTime(queues.get(3).getAverageServiceTime());
                fourthQueueResult.setAverageWaitingTime(queues.get(3).getAverageWaitingTime());
                fifthQueueResult.setAverageServiceTime(queues.get(4).getAverageServiceTime());
                fifthQueueResult.setAverageWaitingTime(queues.get(4).getAverageWaitingTime());
                break;
            }
            default:break;
        }
        resultGrid.setVisible(true);
        logView.setText(logView.getText() + "\n SIMULATION FINISHED\n");
        visual.stopThread();
        generator.stopSimulation();
        for (QueueThread i : threads)
            i.stopThread();
        this.stop = false;
        logView.setScrollTop(Double.MAX_VALUE);
    }
}
