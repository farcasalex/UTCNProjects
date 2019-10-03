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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WaiterViewController implements Initializable {

    private Restaurant restaurant = new Restaurant();
    private RestaurantSerializator serializator = new RestaurantSerializator();
    private TableService tableService = new TableService();

    @FXML
    private TableView<?> baseProductTable;

    @FXML
    private TableView<?> compositeProductTable;

    @FXML
    private TextField idNewOrder;

    @FXML
    private Label idNewLabel;

    @FXML
    private TextField tableNewOrder;

    @FXML
    private Label tableLable;

    @FXML
    private Button submitNewOrder;

    @FXML
    private Button add;

    @FXML
    private Label idFAddLabel;

    @FXML
    private Button submittAdd;

    @FXML
    private ComboBox<String> idAdd;

    @FXML
    private Label idFinalizeLabel;

    @FXML
    private Button bill;

    @FXML
    private ComboBox<String> idFinalze;

    private void generateOrdersID() {
        ObservableList<String> list = FXCollections.observableArrayList();
        restaurant.getOrders().forEach((order, menuItems) -> {
            list.add(String.valueOf(order.getOrderId()));
        });
        idAdd.setItems(list);
        idFinalze.setItems(list);
    }

    @FXML
    void AddAction(ActionEvent event) {
        generateOrdersID();
        idFAddLabel.setVisible(true);
        idAdd.setVisible(true);
        submittAdd.setVisible(true);
    }

    @FXML
    void submitAddAction(ActionEvent event) {
        if (idAdd.getValue() != ""){
            Order order = restaurant.getOrderById(Integer.valueOf(idAdd.getValue()));
            if (baseProductTable.getSelectionModel().getSelectedItem() != null) {
                BaseProduct product = (BaseProduct) baseProductTable.getSelectionModel().getSelectedItem();
                restaurant.getOrders().get(order).add(product);
                this.setNews(product.getName());
            }
            if (compositeProductTable.getSelectionModel().getSelectedItem() != null) {
                CompositeProduct temp = (CompositeProduct) compositeProductTable.getSelectionModel().getSelectedItem();
                CompositeProduct product = restaurant.getProductByName(temp.getName());
                restaurant.getOrders().get(order).add(product);
                this.setNews(product.getName());
            }
            idFAddLabel.setVisible(false);
            idAdd.setVisible(false);
            submittAdd.setVisible(false);
        }
    }

    @FXML
    void CloseAction(ActionEvent event) {
        serializator.save(restaurant);
        Stage stage = (Stage) add.getScene().getWindow();
        stage.close();
    }

    @FXML
    void FinalizeAction(ActionEvent event) {
        idFinalizeLabel.setVisible(true);
        bill.setVisible(true);
        idFinalze.setVisible(true);
        generateOrdersID();
    }

    @FXML
    void billAction(ActionEvent event) {
        if (idFinalze.getValue() != ""){
            Order order = restaurant.getOrderById(Integer.valueOf(idFinalze.getValue()));
            ArrayList<MenuItem> orderList = restaurant.getOrders().get(order);
            generateBill(order.getOrderId(), orderList);
            restaurant.getOrders().remove(order);
            idFinalizeLabel.setVisible(false);
            bill.setVisible(false);
            idFinalze.setVisible(false);
        }
    }

    public void generateBill(int id, ArrayList<MenuItem> orderList){
        PrintWriter bill = null;
        int price = 0;
        try {
            bill = new PrintWriter("order" + id + ".txt", "UTF-8");
            for (MenuItem item : orderList){
                if (item instanceof BaseProduct){
                    bill.println(((BaseProduct) item).getName() + "\t\t\tPRICE: " + item.computePrice());
                    price += item.computePrice();
                }
                if (item instanceof CompositeProduct){
                    bill.println(((CompositeProduct) item).getName() + "\t\t\tPRICE: " + item.computePrice());
                    price += item.computePrice();
                }
            }
            bill.println("TOTAL\t\t\t\t\t" + price);
            bill.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

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
    void NewAction(ActionEvent event) {
        idNewLabel.setVisible(true);
        tableNewOrder.setVisible(true);
        submitNewOrder.setVisible(true);
        idNewOrder.setVisible(true);
        tableLable.setVisible(true);
    }

    @FXML
    void submitNewOrderAction(ActionEvent event) {
        try{
            if(idNewOrder.getText() != "" && tableNewOrder.getText() != ""){
                Order order = new Order(Integer.valueOf(idNewOrder.getText()), Integer.valueOf(tableNewOrder.getText()));
                ArrayList<MenuItem> list = new ArrayList<>();
                if (baseProductTable.getSelectionModel().getSelectedItem() != null) {
                    BaseProduct product = (BaseProduct) baseProductTable.getSelectionModel().getSelectedItem();
                    list.add(product);
                    this.setNews(product.getName());
                }
                if (compositeProductTable.getSelectionModel().getSelectedItem() != null) {
                    CompositeProduct product = (CompositeProduct) compositeProductTable.getSelectionModel().getSelectedItem();
                    list.add(product);
                    this.setNews(product.getName());
                }
                restaurant.getOrders().putIfAbsent(order, list);
                idNewLabel.setVisible(false);
                tableNewOrder.setVisible(false);
                submitNewOrder.setVisible(false);
                idNewOrder.setVisible(false);
                tableLable.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    private void updateTables() {
        tableService.emptyTable(baseProductTable);
        tableService.emptyTable(compositeProductTable);
        for(businessLayer.MenuItem item: restaurant.getProducts()){
            if (item instanceof BaseProduct){
                tableService.dataToTable(item, baseProductTable);
                tableService.headerGenerate(item, compositeProductTable);
            }
            else{
                tableService.dataToTable(item, compositeProductTable);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idNewLabel.setVisible(false);
        idFAddLabel.setVisible(false);
        idFinalizeLabel.setVisible(false);
        idAdd.setVisible(false);
        tableNewOrder.setVisible(false);
        tableLable.setVisible(false);
        submitNewOrder.setVisible(false);
        submittAdd.setVisible(false);
        bill.setVisible(false);
        idNewOrder.setVisible(false);
        idFinalze.setVisible(false);
        updateTables();
    }

    private PropertyChangeSupport support;
    private String news;

    public WaiterViewController() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setNews(String value) {
        support.firePropertyChange("news", this.news, value);
        this.news = value;
    }
}




