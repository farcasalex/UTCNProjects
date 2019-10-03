//Created by Farcas Alexandru
//UTCN 2019
//14/05/2019
package controller;

import businessLayer.*;
import businessLayer.MenuItem;
import dataLayer.RestaurantSerializator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerViewController implements Initializable {

    private Restaurant restaurant = new Restaurant();
    private TableService tableService = new TableService();
    private RestaurantSerializator serializator = new RestaurantSerializator();

    @FXML
    private TableView<?> productTable;

    @FXML
    private Button add;

    @FXML
    private TextField nameAdd;

    @FXML
    private Label nameLabelAdd;

    @FXML
    private Label priceLabelAdd;

    @FXML
    private TextField priceAdd;

    @FXML
    private Button plusAdd;

    @FXML
    private Button submitAdd;

    @FXML
    private Button delete;

    @FXML
    private ComboBox<String> tableSelect;

    @FXML
    void CloseAction(ActionEvent event) {
        serializator.save(restaurant);
        Stage stage = (Stage) add.getScene().getWindow();
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

    @FXML
    void addAction(ActionEvent event) {
        nameLabelAdd.setVisible(true);
        nameAdd.setVisible(true);
        priceLabelAdd.setVisible(true);
        priceAdd.setVisible(true);
        if (tableSelect.getValue() != "Base Products"){
            plusAdd.setVisible(true);
        }
        submitAdd.setVisible(true);
    }

    @FXML
    void deleteAction(ActionEvent event) {
        if (productTable.getSelectionModel().getSelectedItem() != null) {
            MenuItem item = (MenuItem) productTable.getSelectionModel().getSelectedItem();
            restaurant.getProducts().remove(item);
            updateTable();
        }
    }

    @FXML
    void editAction(ActionEvent event) {

    }

    @FXML
    void plusAddAction(ActionEvent event) {

    }

    @FXML
    void submitAddAction(ActionEvent event) {
        try {
            if (tableSelect.getValue() == "Base Products"){
                if (nameAdd.getText() != "" && priceAdd.getText() != ""){
                    BaseProduct baseProduct = new BaseProduct(Integer.valueOf(priceAdd.getText()), nameAdd.getText());
                    restaurant.getProducts().add(baseProduct);
                    updateTable();
                    nameLabelAdd.setVisible(false);
                    nameAdd.setVisible(false);
                    priceLabelAdd.setVisible(false);
                    priceAdd.setVisible(false);
                    plusAdd.setVisible(false);
                    submitAdd.setVisible(false);
                }
                else{
                    throw new Exception("First input data!");
                }
            }
            else {
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changeTableAction(ActionEvent event) {
        updateTable();
    }

    private void updateTable() {
        tableService.emptyTable(productTable);
        if (tableSelect.getValue() == "Base Products"){
            for(MenuItem item: restaurant.getProducts()){
                if (item instanceof BaseProduct){
                    tableService.dataToTable(item, productTable);
                }
            }
        }
        else{
            for(MenuItem item: restaurant.getProducts()){
                if (item instanceof CompositeProduct){
                    tableService.dataToTable(item, productTable);
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabelAdd.setVisible(false);
        nameAdd.setVisible(false);
        priceLabelAdd.setVisible(false);
        priceAdd.setVisible(false);
        plusAdd.setVisible(false);
        submitAdd.setVisible(false);

        ObservableList<String> tableSelectList = FXCollections.observableArrayList("Base Products", "Composite Products");
        tableSelect.setItems(tableSelectList);
        tableSelect.setValue("Base Products");
        updateTable();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}

