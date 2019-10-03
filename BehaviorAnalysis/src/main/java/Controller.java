//Created by Farcas Alexandru
//UTCN 2019
//04/06/2019
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextArea display;

    @FXML
    private Button close;

    @FXML
    void CloseAction(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    Monitor monitor = new Monitor("Activities.txt");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        display.setText("Count how many days of monitored data appears in the log: " + monitor.monitoredDays());
        display.setText(display.getText() + "\nCount how many times has appeared each activity over the entire monitoring period." +
                "Return a map of type <String, Int> representing the mapping of activities to their count:\n");
        monitor.activitiesCount().forEach((s, integer) -> {
            display.setText(display.getText() + "[" + integer + "] " + s + "\n");
        });
        display.setText(display.getText() + "\nFor each line from the file map for the activity label the duration recorded on that line (end_time-start_time):\n" + monitor.activityDuration());
        display.setText(display.getText() + "\nFor each activity compute the entire duration over the monitoring period:\n");
        monitor.activitiesTotalDuration().forEach((s, integer) -> {
            display.setText(display.getText() + "[" + integer + " minutes]" + s + "\n");
        });
        display.setText(display.getText() + "\nFilter the activities that have 90% of the monitoring records with duration less than 5 minutes:\n" + monitor.filter());
    }
}
