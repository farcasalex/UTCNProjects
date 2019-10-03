//Created by Farcas Alexandru
//UTCN 2019
//20/04/2019
package controller;

import businessLayer.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import dataAccessLayer.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Client;
import model.Order;
import model.Product;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    private ClientDAO clientDAO = new ClientDAO();
    private ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private ErrorMessage errorMessage = new ErrorMessage();

    @FXML
    private AnchorPane header;

    @FXML
    private Button close;

    @FXML
    private Circle circle;

    @FXML
    private TableView<?> ordersProductsTable;

    @FXML
    private TableView<?> ordersClientsTable;

    @FXML
    private TableView<?> ordersOrdersTable;

    @FXML
    private TextField ordersProductSearch;

    @FXML
    private ComboBox<String> ordersProductSearchFilter;

    @FXML
    private TextField ordersClientSearch;

    @FXML
    private ComboBox<String> ordersClientSearchFilter;

    @FXML
    private TextField ordersOrderSearch;

    @FXML
    private ComboBox<String> ordersOrderSearchFilter;

    @FXML
    private TextField newOrderAmount;

    @FXML
    private TextField newOrderClient;

    @FXML
    private TextField newOrderProduct;

    @FXML
    private TextField placeOrderError;

    @FXML
    private TableView<?> clientTable;

    @FXML
    private TextField clientSearch;

    @FXML
    private ComboBox<String> clientSearchFilter;

    @FXML
    private AnchorPane clientEditBox;

    @FXML
    private TextField clientFirstname;

    @FXML
    private TextField clientName;

    @FXML
    private TextField clientAddress;

    @FXML
    private TextField clientEditError;

    @FXML
    private AnchorPane clientAddBox;

    @FXML
    private TextField clientFirstnameAdd;

    @FXML
    private TextField clientNameAdd;

    @FXML
    private TextField clientAddressAdd;

    @FXML
    private TextField clientAddError;

    @FXML
    private TextField productSearch;

    @FXML
    private TableView<?> productTable;

    @FXML
    private ComboBox<String> productSearchFilter;

    @FXML
    private AnchorPane productEditBox;

    @FXML
    private TextField productCategory;

    @FXML
    private TextField productName;

    @FXML
    private TextField productStock;

    @FXML
    private TextField productEditError;

    @FXML
    private AnchorPane productAddBox;

    @FXML
    private TextField productCategoryAdd;

    @FXML
    private TextField productNameAdd;

    @FXML
    private TextField productStockAdd;

    @FXML
    private TextField productAddError;

    @FXML
    void clientEditAction(ActionEvent event) {
        clientEditBox.setVisible(true);
        if (clientTable.getSelectionModel().getSelectedItem() != null) {
            Client client = (Client) clientTable.getSelectionModel().getSelectedItem();
            clientFirstname.setText(client.getFirstName());
            clientName.setText(client.getName());
            clientAddress.setText(client.getAddress());
        }
    }

    @FXML
    void clientEditSubmitAction(ActionEvent event) {
        try{
            Client client = (Client) clientTable.getSelectionModel().getSelectedItem();
            client.setFirstName(clientFirstname.getText());
            client.setName(clientName.getText());
            client.setAddress(clientAddress.getText());
            clientDAO.update(client);
            updateTables();
        } catch (Exception e) {
            errorMessage.display(clientEditError, "Invalid input data!");
        }
    }

    @FXML
    void clientRemoveAction(ActionEvent event) {
        if (clientTable.getSelectionModel().getSelectedItem() != null) {
            Client client = (Client) clientTable.getSelectionModel().getSelectedItem();
            clientDAO.delete(client.getId());
            updateTables();
        }
    }

    @FXML
    void placeOrderAction(ActionEvent event) {
        int productId = 0;
        int amount = 0;
        int clientId = 0;
        int stock = 0;

        if (ordersClientsTable.getSelectionModel().getSelectedItem() != null){
            Client client = (Client) ordersClientsTable.getSelectionModel().getSelectedItem();
            clientId = client.getId();
        }else{
            errorMessage.display(placeOrderError, "Select client!");
            return;
        }
        if (ordersProductsTable.getSelectionModel().getSelectedItem() != null){
            Product product = (Product) ordersProductsTable.getSelectionModel().getSelectedItem();
            productId = product.getId();
            stock = product.getStock();

        }else{
            errorMessage.display(placeOrderError, "Select product!");
            return;
        }
        if (newOrderAmount.getText().isEmpty()){
            errorMessage.display(placeOrderError, "Enter order amount!");
            return;
        }else {
            amount = Integer.valueOf(newOrderAmount.getText());
            if (amount > stock) {
                errorMessage.display(placeOrderError, "Insufficient stock!");
                return;
            }
        }

        try{
            orderDAO.insert(productId, amount, clientId);
            Product product = productDAO.findById(productId);
            product.setStock(product.getStock() - amount);
            productDAO.update(product);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            newOrderProduct.setText("");
            newOrderClient.setText("");
            newOrderClient.setText("");
            updateTables();
            generatePDF(orderDAO.findAll().get(orderDAO.findAll().size() - 1));
        }
    }

    @FXML
    void productEditAction(ActionEvent event) {
        productEditBox.setVisible(true);
        if (productTable.getSelectionModel().getSelectedItem() != null) {
            Product product = (Product) productTable.getSelectionModel().getSelectedItem();
            productCategory.setText(product.getCategory());
            productName.setText(product.getProductName());
            productStock.setText(String.valueOf(product.getStock()));
        }
    }

    @FXML
    void productEditSubmitAction(ActionEvent event) {
        try{
            Product product = (Product) productTable.getSelectionModel().getSelectedItem();
            product.setProductName(productName.getText());
            product.setCategory(productCategory.getText());
            product.setStock(Integer.valueOf(productStock.getText()));
            productDAO.update(product);
            updateTables();
        } catch (Exception e) {
            errorMessage.display(productEditError, "Invalid input data!");
        }
    }

    @FXML
    void productRemoveAction(ActionEvent event) {
        if (productTable.getSelectionModel().getSelectedItem() != null) {
            Product product = (Product) productTable.getSelectionModel().getSelectedItem();
            productDAO.delete(product.getId());
            updateTables();
        }
    }

    @FXML
    void CloseAction(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public void updateTables(){
        emptyTable(clientTable);
        emptyTable(ordersClientsTable);
        emptyTable(productTable);
        emptyTable(ordersProductsTable);
        emptyTable(ordersOrdersTable);

        for (Client client : clientDAO.findAll()){
            Reflection.dataToTable(client, clientTable);
            Reflection.dataToTable(client, ordersClientsTable);
        }
        for (Product product : productDAO.findAll()){
            Reflection.dataToTable(product, productTable);
            Reflection.dataToTable(product, ordersProductsTable);
        }
        for (Order order : orderDAO.findAll()){
            Reflection.dataToTable(order, ordersOrdersTable);
        }

        productEditBox.setVisible(false);
        productAddBox.setVisible(false);
        clientEditBox.setVisible(false);
        clientAddBox.setVisible(false);
    }

    @FXML
    void clientAddAction(ActionEvent event) {
        clientAddBox.setVisible(true);
    }

    @FXML
    void clientAddSubmitAction(ActionEvent event) {
        if (clientNameAdd.getText().equals("") == false && clientFirstnameAdd.getText().equals("") == false && clientAddressAdd.getText().equals("") == false){
            try {
                Client client = new Client();
                client.setName(clientNameAdd.getText());
                client.setFirstName(clientFirstnameAdd.getText());
                client.setAddress(clientAddressAdd.getText());
                clientDAO.insert(client);
                updateTables();
            }catch (Exception e){
                errorMessage.display(clientAddError, "Invalid input data!");
            }
        }else {
            errorMessage.display(clientAddError, "Fields are not optional!");
        }
    }

    @FXML
    void productAddAction(ActionEvent event) {
        productAddBox.setVisible(true);
    }

    @FXML
    void productAddSubmitAction(ActionEvent event) {
        if (productNameAdd.getText().equals("") == false && productCategoryAdd.getText().equals("") == false &&productStockAdd.getText().equals("") == false){
            try {
                Product product = new Product();
                product.setProductName(productNameAdd.getText());
                product.setCategory(productCategoryAdd.getText());
                product.setStock(Integer.valueOf(productStockAdd.getText()));
                productDAO.insert(product);
                updateTables();
            }catch (Exception e){
                errorMessage.display(productAddError, "Invalid input data!");
            }
        }else {
            errorMessage.display(productAddError, "Fields are not optional!");
        }
    }

    @FXML
    void ordersClientAction(MouseEvent event) {
        Client client = (Client) ordersClientsTable.getSelectionModel().getSelectedItem();
        newOrderClient.setText(client.getFirstName() + " " + client.getName());
    }

    @FXML
    void ordersProductAction(MouseEvent event) {
        try {
            Product product = (Product) ordersProductsTable.getSelectionModel().getSelectedItem();
            newOrderProduct.setText(product.getProductName() + " " + product.getCategory());
        } catch (Exception e) {

        }
    }

    @FXML
    void productSearchAction(ActionEvent event) {
        String string = productSearch.getText();
        List<Product> productList = new ArrayList<>();
        if (!string.equals("")){
            switch (productSearchFilter.getValue()){
                case "Id": productList = productDAO.filterById(string);
                break;
                case "Category": productList = productDAO.filterByCategory(string);
                break;
                case "Product Name": productList = productDAO.filterByProductName(string);
                break;
                case "Stock": productList = productDAO.filterByStock(string);
                break;
            }
            emptyTable(productTable);
            for (Product product : productList){
                Reflection.dataToTable(product, productTable);
            }
        }else{
            updateTables();
        }
    }

    @FXML
    void ordersProductSearchAction(ActionEvent event) {
        String string = ordersProductSearch.getText();
        List<Product> productList = new ArrayList<>();
        if (!string.equals("")){
            switch (ordersProductSearchFilter.getValue()){
                case "Id": productList = productDAO.filterById(string);
                break;
                case "Category": productList = productDAO.filterByCategory(string);
                    break;
                case "Product Name": productList = productDAO.filterByProductName(string);
                break;
                case "Stock": productList = productDAO.filterByStock(string);
                break;
            }
            emptyTable(ordersProductsTable);
            for (Product product : productList){
                Reflection.dataToTable(product, ordersProductsTable);
            }
        }else{
            updateTables();
        }
    }

    @FXML
    void ordersClientSearchAction(ActionEvent event) {
        String string = ordersClientSearch.getText();
        List<Client> clientList = new ArrayList<>();
        if (!string.equals("")){
            switch (ordersClientSearchFilter.getValue()){
                case "Id": clientList = clientDAO.filterById(string);
                    break;
                case "First Name": clientList = clientDAO.filterByFirstName(string);
                    break;
                case "Name": clientList = clientDAO.filterByName(string);
                    break;
                case "Address": clientList = clientDAO.filterByAddress(string);
                    break;
            }
            emptyTable(ordersClientsTable);
            for (Client client : clientList){
                Reflection.dataToTable(client, ordersClientsTable);
            }
        }else{
            updateTables();
        }
    }

    @FXML
    void clientSearchAction(ActionEvent event) {
        String string = clientSearch.getText();
        List<Client> clientList = new ArrayList<>();
        if (!string.equals("")){
            switch (clientSearchFilter.getValue()){
                case "Id": clientList = clientDAO.filterById(string);
                break;
                case "First Name": clientList = clientDAO.filterByFirstName(string);
                break;
                case "Name": clientList = clientDAO.filterByName(string);
                break;
                case "Address": clientList = clientDAO.filterByAddress(string);
                    break;
            }
            emptyTable(clientTable);
            for (Client client : clientList){
                Reflection.dataToTable(client, clientTable);
            }
        }else{
            updateTables();
        }
    }

    @FXML
    void orderSearchAction(ActionEvent event) {
        String string = ordersOrderSearch.getText();
        List<Order> orderList = new ArrayList<>();
        if (!string.equals("")){
            switch (ordersOrderSearchFilter.getValue()){
                case "Id": orderList = orderDAO.filterById(string);
                    break;
                case "First Name": orderList = orderDAO.filterByFirstName(string);
                    break;
                case "Name": orderList = orderDAO.filterByName(string);
                    break;
                case "Category": orderList = orderDAO.filterByCategory(string);
                    break;
                case "Product Name": orderList = orderDAO.filterByProductName(string);
                    break;
                case "Amount": orderList = orderDAO.filterByAmount(string);
                    break;
            }
            emptyTable(ordersOrdersTable);
            for (Order order : orderList){
                Reflection.dataToTable(order, ordersOrdersTable);
            }
        }else{
            updateTables();
        }
    }

    public void emptyTable(TableView table){
        table.getItems().clear();
    }

    public void generatePDF(Order order){
        Document document = new Document();
        document.setPageSize(PageSize.A6);
        String FILE_NAME = "bills\\order_" + order.getId() + ".pdf";
        Chunk dots = new Chunk(new DottedLineSeparator());
        Chunk line = new Chunk(new LineSeparator());

        try {
            PdfWriter.getInstance(document, new FileOutputStream(FILE_NAME));
            document.open();

            Font font = new Font();
            font.setStyle(Font.BOLD);
            font.setSize(30);

            String filename = ".\\src\\main\\resources\\media\\logo.png";
            Image image = Image.getInstance(filename);
            image.scaleToFit(200, 60);
            image.setAlignment(Element.ALIGN_CENTER);
            document.add(image);
            document.add(Chunk.NEWLINE);

            Paragraph p = new Paragraph("ORDER BILL", font);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(Chunk.NEWLINE);

            p = new Paragraph("ORDER ID");
            p.add(dots);
            p.add(String.valueOf(order.getId()));
            document.add(p);

            p = new Paragraph("PRODUCT CATEGORY");
            p.add(dots);
            p.add(String.valueOf(order.getCategory()));
            document.add(p);

            p = new Paragraph("PRODUCT NAME");
            p.add(dots);
            p.add(String.valueOf(order.getProductName()));
            document.add(p);

            p = new Paragraph("AMOUNT");
            p.add(dots);
            p.add(String.valueOf(order.getAmount()));
            document.add(p);

            p = new Paragraph("CUSTOMER");
            p.add(dots);
            p.add(order.getName() + " " + order.getFirstName());
            document.add(p);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(line);
            Date date = new Date();
            p = new Paragraph(date.toString());
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> productsTableHeader = FXCollections.observableArrayList("Id", "Category", "Product Name", "Stock");
        ObservableList<String> clientsTableHeader = FXCollections.observableArrayList("Id", "First Name", "Name", "Address");
        ObservableList<String> ordersTableHeader = FXCollections.observableArrayList("Id", "First Name", "Name", "Category", "Product Name", "Amount");
        productSearchFilter.setItems(productsTableHeader);
        productSearchFilter.setValue("Id");
        clientSearchFilter.setItems(clientsTableHeader);
        clientSearchFilter.setValue("Id");
        ordersClientSearchFilter.setItems(clientsTableHeader);
        ordersClientSearchFilter.setValue("Id");
        ordersProductSearchFilter.setItems(productsTableHeader);
        ordersProductSearchFilter.setValue("Id");
        ordersOrderSearchFilter.setItems(ordersTableHeader);
        ordersOrderSearchFilter.setValue("Id");

        updateTables();
    }
}
