//Created by Farcas Alexandru
//UTCN 2019
//22/03/2019
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import thread.*;
import model.*;

public class MainViewController implements Initializable {

    @FXML
    private TextArea logView;

    @FXML
    private Button twentythree1;

    @FXML
    private Button twentytwo1;

    @FXML
    private Button twentyone1;

    @FXML
    private Button twenty1;

    @FXML
    private Button nineteen1;

    @FXML
    private Button eighteen1;

    @FXML
    private Button twelve1;

    @FXML
    private Button thirteen1;

    @FXML
    private Button fourteen1;

    @FXML
    private Button fifteen1;

    @FXML
    private Button sixteen1;

    @FXML
    private Button seventeen1;

    @FXML
    private Button twentyfive1;

    @FXML
    private Button twentyfour1;

    @FXML
    private Button two1;

    @FXML
    private Button three1;

    @FXML
    private Button four1;

    @FXML
    private Button five1;

    @FXML
    private Button six1;

    @FXML
    private Button seven1;

    @FXML
    private Button eight1;

    @FXML
    private Button nine1;

    @FXML
    private Button ten1;

    @FXML
    private Button eleven1;

    @FXML
    private Button twentysix1;

    @FXML
    private Button one1;

    @FXML
    private Button twentyseven1;

    @FXML
    private Label firstLabel;

    @FXML
    private Button one2;

    @FXML
    private Button two2;

    @FXML
    private Button three2;

    @FXML
    private Button four2;

    @FXML
    private Button five2;

    @FXML
    private Button six2;

    @FXML
    private Button seven2;

    @FXML
    private Button eight2;

    @FXML
    private Button nine2;

    @FXML
    private Button ten2;

    @FXML
    private Button eleven2;

    @FXML
    private Button twelve2;

    @FXML
    private Button thirteen2;

    @FXML
    private Button fourteen2;

    @FXML
    private Button fifteen2;

    @FXML
    private Button sixteen2;

    @FXML
    private Button seventeen2;

    @FXML
    private Button eighteen2;

    @FXML
    private Button nineteen2;

    @FXML
    private Button twenty2;

    @FXML
    private Button twentyone2;

    @FXML
    private Button twentytwo2;

    @FXML
    private Button twentythree2;

    @FXML
    private Button twentyfour2;

    @FXML
    private Button twentyfive2;

    @FXML
    private Button twentysix2;

    @FXML
    private Button twentyseven2;

    @FXML
    private Label secondLabel;

    @FXML
    private Button one3;

    @FXML
    private Button two3;

    @FXML
    private Button three3;

    @FXML
    private Button four3;

    @FXML
    private Button five3;

    @FXML
    private Button six3;

    @FXML
    private Button seven3;

    @FXML
    private Button eight3;

    @FXML
    private Button nine3;

    @FXML
    private Button ten3;

    @FXML
    private Button eleven3;

    @FXML
    private Button twelve3;

    @FXML
    private Button thirteen3;

    @FXML
    private Button fourteen3;

    @FXML
    private Button fifteen3;

    @FXML
    private Button sixteen3;

    @FXML
    private Button seventeen3;

    @FXML
    private Button eighteen3;

    @FXML
    private Button nineteen3;

    @FXML
    private Button twenty3;

    @FXML
    private Button twentyone3;

    @FXML
    private Button twentytwo3;

    @FXML
    private Button twentythree3;

    @FXML
    private Button twentyfour3;

    @FXML
    private Button twentyfive3;

    @FXML
    private Button twentysix3;

    @FXML
    private Button twentyseven3;

    @FXML
    private Label thirdLabel;

    @FXML
    private Button one4;

    @FXML
    private Button two4;

    @FXML
    private Button three4;

    @FXML
    private Button four4;

    @FXML
    private Button five4;

    @FXML
    private Button six4;

    @FXML
    private Button seven4;

    @FXML
    private Button eight4;

    @FXML
    private Button nine4;

    @FXML
    private Button ten4;

    @FXML
    private Button eleven4;

    @FXML
    private Button twelve4;

    @FXML
    private Button thirteen4;

    @FXML
    private Button fourteen4;

    @FXML
    private Button fifteen4;

    @FXML
    private Button sixteen4;

    @FXML
    private Button seventeen4;

    @FXML
    private Button eighteen4;

    @FXML
    private Button nineteen4;

    @FXML
    private Button twenty4;

    @FXML
    private Button twentyone4;

    @FXML
    private Button twentytwo4;

    @FXML
    private Button twentythree4;

    @FXML
    private Button twentyfour4;

    @FXML
    private Button twentyfive4;

    @FXML
    private Button twentysix4;

    @FXML
    private Button twentyseven4;

    @FXML
    private Label fourthLabel;

    @FXML
    private Button one5;

    @FXML
    private Button two5;

    @FXML
    private Button three5;

    @FXML
    private Button four5;

    @FXML
    private Button five5;

    @FXML
    private Button six5;

    @FXML
    private Button seven5;

    @FXML
    private Button eight5;

    @FXML
    private Button nine5;

    @FXML
    private Button ten5;

    @FXML
    private Button eleven5;

    @FXML
    private Button twelve5;

    @FXML
    private Button thirteen5;

    @FXML
    private Button fourteen5;

    @FXML
    private Button fifteen5;

    @FXML
    private Button sixteen5;

    @FXML
    private Button seventeen5;

    @FXML
    private Button eighteen5;

    @FXML
    private Button nineteen5;

    @FXML
    private Button twenty5;

    @FXML
    private Button twentyone5;

    @FXML
    private Button twentytwo5;

    @FXML
    private Button twentythree5;

    @FXML
    private Button twentyfour5;

    @FXML
    private Button twentyfive5;

    @FXML
    private Button twentysix5;

    @FXML
    private Button twentyseven5;

    @FXML
    private Label fifthLabel;

    @FXML
    private Rectangle firstCheckout;

    @FXML
    private Rectangle secondCheckout;

    @FXML
    private Rectangle thirdCheckout;

    @FXML
    private Rectangle fourthCheckout;

    @FXML
    private Rectangle fifthCheckout;

    @FXML
    private TextField minArrival;

    @FXML
    private TextField maxArrival;

    @FXML
    private TextField minService;

    @FXML
    private TextField maxService;

    @FXML
    private TextField simulationInterval;

    @FXML
    private ComboBox<String> queueNumber;

    @FXML
    private Label error;

    @FXML
    private Button abort;

    @FXML
    private Button startSimulation;

    @FXML
    private GridPane resultGrid;

    @FXML
    private TextField averageWaitingTime;

    @FXML
    private TextField averageServiceTime;

    @FXML
    private TextField emptyTime;

    @FXML
    private ComboBox<String> resultQueueNumber;

    @FXML
    private Label peekLabel;

    @FXML
    private TextField peekHour;

    private Button[] firstQueue;
    private Button[] secondQueue;
    private Button[] thirdQueue;
    private Button[] fourthQueue;
    private Button[] fifthQueue;
    private DataValidator validator;
    private Simulation simulation = new Simulation();
    private ButtonDisableThread buttonDisableThread = new ButtonDisableThread();
    LocalDateTime clock = LocalDateTime.now();
    private SimulationResult firstQueueResult = new SimulationResult();
    private SimulationResult secondQueueResult = new SimulationResult();
    private SimulationResult thirdQueueResult = new SimulationResult();
    private SimulationResult fourthQueueResult = new SimulationResult();
    private SimulationResult fifthQueueResult = new SimulationResult();
    private int numberOfUsedQueues;

    @FXML
    void AbortAction() {
        simulation.stopSimulation();
        buttonDisableThread.stopThread();
    }

    @FXML
    void StartAction() {
        try {
            validator = new DataValidator(minArrival.getText(), maxArrival.getText(), minService.getText(), maxService.getText(), simulationInterval.getText());
            ObservableList<String> resultOptions = FXCollections.observableArrayList("total");
            switch (Integer.parseInt(queueNumber.getValue())){
                case 1:{
                    resultOptions.add("1");
                    firstCheckout.setVisible(false);
                    secondCheckout.setVisible(false);
                    thirdCheckout.setVisible(true);
                    fourthCheckout.setVisible(false);
                    fifthCheckout.setVisible(false);
                    break;
                }
                case 2:{
                    resultOptions.addAll("1", "2");
                    firstCheckout.setVisible(false);
                    secondCheckout.setVisible(true);
                    thirdCheckout.setVisible(false);
                    fourthCheckout.setVisible(true);
                    fifthCheckout.setVisible(false);
                    break;
                }
                case 3:{
                    resultOptions.addAll("1", "2", "3");
                    firstCheckout.setVisible(true);
                    secondCheckout.setVisible(true);
                    thirdCheckout.setVisible(true);
                    fourthCheckout.setVisible(false);
                    fifthCheckout.setVisible(false);
                    break;
                }
                case 4:{
                    resultOptions.addAll("1", "2", "3", "4");
                    firstCheckout.setVisible(true);
                    secondCheckout.setVisible(true);
                    thirdCheckout.setVisible(true);
                    fourthCheckout.setVisible(true);
                    fifthCheckout.setVisible(false);
                    break;
                }
                case 5:{
                    resultOptions.addAll("1", "2", "3", "4", "5");
                    firstCheckout.setVisible(true);
                    secondCheckout.setVisible(true);
                    thirdCheckout.setVisible(true);
                    fourthCheckout.setVisible(true);
                    fifthCheckout.setVisible(true);
                    break;
                }
                default: break;
            }
            resultQueueNumber.setItems(resultOptions);
            clock = LocalDateTime.now();
            numberOfUsedQueues = Integer.parseInt(queueNumber.getValue());
            logView.setText(logView.getText() + "\n" + " SIMULATION_STARTED: " + clock.getHour() + ":" + clock.getMinute() + ":" + clock.getSecond());
            simulation = new Simulation(validator.getMinArrival(),validator.getMaxArrival(),validator.getMinService(),validator.getMaxService(),
                    numberOfUsedQueues, validator.getSimulationInterval(), firstQueue, secondQueue, thirdQueue, fourthQueue, fifthQueue, logView,
                    firstQueueResult, secondQueueResult, thirdQueueResult, fourthQueueResult, fifthQueueResult, resultGrid);
            simulation.start();
            buttonDisableThread = new ButtonDisableThread(startSimulation, abort, validator.getSimulationInterval());
            buttonDisableThread.start();
            resultQueueNumber.setValue("total");
        } catch (Exception e) {
            e.printStackTrace();
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.display(error);
        }
    }

    @FXML
    void SelectAction() {
        switch (resultQueueNumber.getValue()){
            case "total":{
                switch (numberOfUsedQueues){
                    case 1:{
                        emptyTime.setText(String.valueOf(thirdQueueResult.getEmptyTime()));
                        averageServiceTime.setText(String.valueOf(thirdQueueResult.getAverageServiceTime()));
                        averageWaitingTime.setText(String.valueOf(thirdQueueResult.getAverageWaitingTime()));
                        peekHour.setText(String.valueOf(thirdQueueResult.getPeekHour()));
                        break;
                    }
                    case 2: {
                        emptyTime.setText(String.valueOf(secondQueueResult.getEmptyTime() + fourthQueueResult.getEmptyTime()));
                        averageServiceTime.setText(String.valueOf((secondQueueResult.getAverageServiceTime() + fourthQueueResult.getAverageServiceTime()) / 2));
                        averageWaitingTime.setText(String.valueOf((secondQueueResult.getAverageWaitingTime() + fourthQueueResult.getAverageWaitingTime()) / 2));
                        peekHour.setText(String.valueOf((secondQueueResult.getPeekHour() + fourthQueueResult.getPeekHour()) / 2));
                        break;
                    }
                    case 3: {
                        emptyTime.setText(String.valueOf(firstQueueResult.getEmptyTime() + secondQueueResult.getEmptyTime() + thirdQueueResult.getEmptyTime()));
                        averageServiceTime.setText(String.valueOf((firstQueueResult.getAverageServiceTime() + secondQueueResult.getAverageServiceTime() + thirdQueueResult.getAverageServiceTime()) / 3));
                        averageWaitingTime.setText(String.valueOf((firstQueueResult.getAverageWaitingTime() + secondQueueResult.getAverageWaitingTime() + thirdQueueResult.getAverageWaitingTime()) / 3));
                        peekHour.setText(String.valueOf((firstQueueResult.getPeekHour() + secondQueueResult.getPeekHour() + thirdQueueResult.getPeekHour()) / 3));
                        break;
                    }
                    case 4: {
                        emptyTime.setText(String.valueOf(firstQueueResult.getEmptyTime() + secondQueueResult.getEmptyTime() + thirdQueueResult.getEmptyTime() + fourthQueueResult.getEmptyTime()));
                        averageServiceTime.setText(String.valueOf((firstQueueResult.getAverageServiceTime() + secondQueueResult.getAverageServiceTime() + thirdQueueResult.getAverageServiceTime() + fourthQueueResult.getAverageServiceTime()) / 4));
                        averageWaitingTime.setText(String.valueOf((firstQueueResult.getAverageWaitingTime() + secondQueueResult.getAverageWaitingTime() + thirdQueueResult.getAverageWaitingTime() + fourthQueueResult.getAverageWaitingTime()) / 4));
                        peekHour.setText(String.valueOf((firstQueueResult.getPeekHour() + secondQueueResult.getPeekHour() + thirdQueueResult.getPeekHour() + fourthQueueResult.getPeekHour()) / 4));
                        break;
                    }
                    case 5:{
                        emptyTime.setText(String.valueOf(firstQueueResult.getEmptyTime() + secondQueueResult.getEmptyTime() + thirdQueueResult.getEmptyTime() + fourthQueueResult.getEmptyTime() + fifthQueueResult.getEmptyTime()));
                        averageServiceTime.setText(String.valueOf((firstQueueResult.getAverageServiceTime() + secondQueueResult.getAverageServiceTime() + thirdQueueResult.getAverageServiceTime() + fourthQueueResult.getAverageServiceTime() + fifthQueueResult.getAverageServiceTime()) / 5));
                        averageWaitingTime.setText(String.valueOf((firstQueueResult.getAverageWaitingTime() + secondQueueResult.getAverageWaitingTime() + thirdQueueResult.getAverageWaitingTime() + fourthQueueResult.getAverageWaitingTime() + fifthQueueResult.getAverageWaitingTime()) / 5));
                        peekHour.setText(String.valueOf((firstQueueResult.getPeekHour() + secondQueueResult.getPeekHour() + thirdQueueResult.getPeekHour() + fourthQueueResult.getPeekHour() + fifthQueueResult.getPeekHour()) / 5));
                        break;
                    }
                    default:break;
                }
                break;
            }
            case "1":{
                switch (numberOfUsedQueues){
                    case 1:{
                        emptyTime.setText(String.valueOf(thirdQueueResult.getEmptyTime()));
                        averageServiceTime.setText(String.valueOf(thirdQueueResult.getAverageServiceTime()));
                        averageWaitingTime.setText(String.valueOf(thirdQueueResult.getAverageWaitingTime()));
                        peekHour.setText(String.valueOf(thirdQueueResult.getPeekHour()));
                        break;
                    }
                    case 2: {
                        emptyTime.setText(String.valueOf(secondQueueResult.getEmptyTime()));
                        averageServiceTime.setText(String.valueOf(secondQueueResult.getAverageServiceTime()));
                        averageWaitingTime.setText(String.valueOf(secondQueueResult.getAverageWaitingTime()));
                        peekHour.setText(String.valueOf(secondQueueResult.getPeekHour()));
                        break;
                    }
                    default: {
                        emptyTime.setText(String.valueOf(firstQueueResult.getEmptyTime()));
                        averageServiceTime.setText(String.valueOf(firstQueueResult.getAverageServiceTime()));
                        averageWaitingTime.setText(String.valueOf(firstQueueResult.getAverageWaitingTime()));
                        peekHour.setText(String.valueOf(firstQueueResult.getPeekHour()));
                        break;
                    }
                }
                break;
            }
            case "2":{
                switch (numberOfUsedQueues){
                    case 2: {
                        emptyTime.setText(String.valueOf(fourthQueueResult.getEmptyTime()));
                        averageServiceTime.setText(String.valueOf(fourthQueueResult.getAverageServiceTime()));
                        averageWaitingTime.setText(String.valueOf(fourthQueueResult.getAverageWaitingTime()));
                        peekHour.setText(String.valueOf(fourthQueueResult.getPeekHour()));
                        break;
                    }
                    default: {
                        emptyTime.setText(String.valueOf(secondQueueResult.getEmptyTime()));
                        averageServiceTime.setText(String.valueOf(secondQueueResult.getAverageServiceTime()));
                        averageWaitingTime.setText(String.valueOf(secondQueueResult.getAverageWaitingTime()));
                        peekHour.setText(String.valueOf(secondQueueResult.getPeekHour()));
                        break;
                    }
                }
                break;
            }
            case "3":{
                emptyTime.setText(String.valueOf(thirdQueueResult.getEmptyTime()));
                averageServiceTime.setText(String.valueOf(thirdQueueResult.getAverageServiceTime()));
                averageWaitingTime.setText(String.valueOf(thirdQueueResult.getAverageWaitingTime()));
                peekHour.setText(String.valueOf(thirdQueueResult.getPeekHour()));
                break;
            }
            case "4":{
                emptyTime.setText(String.valueOf(fourthQueueResult.getEmptyTime()));
                averageServiceTime.setText(String.valueOf(fourthQueueResult.getAverageServiceTime()));
                averageWaitingTime.setText(String.valueOf(fourthQueueResult.getAverageWaitingTime()));
                peekHour.setText(String.valueOf(fourthQueueResult.getPeekHour()));
                break;
            }
            case "5":{
                emptyTime.setText(String.valueOf(fifthQueueResult.getEmptyTime()));
                averageServiceTime.setText(String.valueOf(fifthQueueResult.getAverageServiceTime()));
                averageWaitingTime.setText(String.valueOf(fifthQueueResult.getAverageWaitingTime()));
                peekHour.setText(String.valueOf(fifthQueueResult.getPeekHour()));
                break;
            }
            default: break;
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> queues = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        queueNumber.setItems(queues);

        firstQueue = new Button[]{one1,two1,three1,four1,five1,six1,seven1,eight1,nine1,ten1,eleven1,twelve1,thirteen1,fourteen1,fifteen1,sixteen1,seventeen1,
                                  eighteen1,nineteen1,twenty1,twentyone1,twentytwo1,twentythree1,twentyfour1,twentyfive1,twentysix1,twentyseven1};

        secondQueue = new Button[]{one2,two2,three2,four2,five2,six2,seven2,eight2,nine2,ten2,eleven2,twelve2,thirteen2,fourteen2,fifteen2,sixteen2,seventeen2,
                eighteen2,nineteen2,twenty2,twentyone2,twentytwo2,twentythree2,twentyfour2,twentyfive2,twentysix2,twentyseven2};

        thirdQueue = new Button[]{one3,two3,three3,four3,five3,six3,seven3,eight3,nine3,ten3,eleven3,twelve3,thirteen3,fourteen3,fifteen3,sixteen3,seventeen3,
                eighteen3,nineteen3,twenty3,twentyone3,twentytwo3,twentythree3,twentyfour3,twentyfive3,twentysix3,twentyseven3};

        fourthQueue = new Button[]{one4,two4,three4,four4,five4,six4,seven4,eight4,nine4,ten4,eleven4,twelve4,thirteen4,fourteen4,fifteen4,sixteen4,seventeen4,
                eighteen4,nineteen4,twenty4,twentyone4,twentytwo4,twentythree4,twentyfour4,twentyfive4,twentysix4,twentyseven4};

        fifthQueue = new Button[]{one5,two5,three5,four5,five5,six5,seven5,eight5,nine5,ten5,eleven5,twelve5,thirteen5,fourteen5,fifteen5,sixteen5,seventeen5,
                eighteen5,nineteen5,twenty5,twentyone5,twentytwo5,twentythree5,twentyfour5,twentyfive5,twentysix5,twentyseven5};

        for (Button i: firstQueue)
            i.setVisible(false);
        for (Button i: secondQueue)
            i.setVisible(false);
        for (Button i: thirdQueue)
            i.setVisible(false);
        for (Button i: fourthQueue)
            i.setVisible(false);
        for (Button i: fifthQueue)
            i.setVisible(false);

        firstLabel.setText("");
        secondLabel.setText("");
        thirdLabel.setText("");
        fourthLabel.setText("");
        fifthLabel.setText("");

        firstCheckout.setVisible(false);
        secondCheckout.setVisible(false);
        thirdCheckout.setVisible(false);
        fourthCheckout.setVisible(false);
        fifthCheckout.setVisible(false);

        logView.setVisible(false);
        logView.setText("");
        resultGrid.setVisible(false);

        abort.setDisable(true);
    }
}

