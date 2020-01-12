import controller.LightBulbController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LightBulb extends Application {

    private static LightBulbController controller;
    private Stage primaryStage;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Pass the server IP as the sole command line argument");
            return;
        }
        LightBulb.controller = new LightBulbController(args[0]);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LightBulbView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.primaryStage.setTitle("Light Bulb");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
}
