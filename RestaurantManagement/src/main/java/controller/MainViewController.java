//Created by Farcas Alexandru
//UTCN 2019
//14/05/2019
package controller;

import businessLayer.*;
import dataLayer.RestaurantSerializator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainViewController {

    private Restaurant restaurant = new Restaurant();
    private RestaurantSerializator serializator = new RestaurantSerializator();
    private ChefViewController chefViewController = new ChefViewController();
    private ManagerViewController managerViewController = new ManagerViewController();
    private WaiterViewController waiterViewController = new WaiterViewController();

    @FXML
    private Button close;

    @FXML
    private Circle circle;

    @FXML
    void ChefWindow(ActionEvent event) {
        Parent root;
        try {
            chefViewController.setRestaurant(restaurant);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ChefView.fxml"));
            loader.setController(chefViewController);
            root = loader.load();
            Stage stage = Main.getPrimaryStage();
            stage.getScene().setRoot(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void ManagerWindow(ActionEvent event) {
        Parent root;
        try {
            managerViewController.setRestaurant(restaurant);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ManagerView.fxml"));
            loader.setController(managerViewController);
            root = loader.load();
            Stage stage = Main.getPrimaryStage();
            stage.getScene().setRoot(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void WaiterWindow(ActionEvent event) {
        Parent root;
        try {
            waiterViewController.setRestaurant(restaurant);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WaiterView.fxml"));
            loader.setController(waiterViewController);
            root = loader.load();
            Stage stage = Main.getPrimaryStage();
            stage.getScene().setRoot(root);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void CloseAction(ActionEvent event) {
        serializator.save(restaurant);
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}

