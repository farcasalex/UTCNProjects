//Created by Farcas Alexandru
//UTCN 2019
//14/05/2019
package controller;

import dataLayer.RestaurantSerializator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    static Stage primaryStage = new Stage();
    private RestaurantSerializator serializator = new RestaurantSerializator();
    private MainViewController controller = new MainViewController();

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage arg0) {
        try {
            controller.setRestaurant(serializator.load());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
            loader.setController(controller);
            GridPane root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
