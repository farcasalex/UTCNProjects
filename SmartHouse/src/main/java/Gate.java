import controller.GateController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Gate extends Application {
    private static GateController controller;
    private Stage primaryStage;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Pass the server IP as the sole command line argument");
            return;
        }
        Gate.controller = new GateController(args[0]);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/GateView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.primaryStage.setTitle("Front Gate");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
}
