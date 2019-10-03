//Created by Farcas Alexandru
//UTCN 2019
//14/05/2019
package controller;

import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;

public class ChefViewController implements PropertyChangeListener, Initializable {

    private Restaurant restaurant = new Restaurant();
    private RestaurantSerializator serializator = new RestaurantSerializator();
    private WaiterViewController waiterViewController = new WaiterViewController();

    @FXML
    private Button close;

    @FXML
    private TextArea display;

    @FXML
    void CloseAction(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    void MenuWindow(ActionEvent event) {
        Parent root;
        try {
            MainViewController controller = new MainViewController();
            controller.setRestaurant(restaurant);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
            loader.setController(controller);
            root = loader.load();
            Stage stage = Main.getPrimaryStage();
            stage.getScene().setRoot(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    private String news;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setNews((String) evt.getNewValue());
        update(news);
    }

    private void update(String news) {
        display.setText(display.getText() + "\n" + news);
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        waiterViewController.addPropertyChangeListener(this);
    }
}
