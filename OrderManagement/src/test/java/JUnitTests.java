//Created by Farcas Alexandru
//UTCN 2019
//21/04/2019

import businessLayer.Reflection;
import dataAccessLayer.ClientDAO;
import dataAccessLayer.DatabaseConnection;
import dataAccessLayer.OrderDAO;
import dataAccessLayer.ProductDAO;
import model.Client;
import model.Order;
import model.Product;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class JUnitTests {

    @Test
    public void dbConnectionTest(){
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null)
                System.out.println("Successfully connected");
            else
                System.out.println("The connection couldn't be made!");
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            preparedStatement = connection.prepareStatement("SELECT * FROM order_management.client");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getInt("Id")+ ")\t" + resultSet.getString("name") + "-" + resultSet.getString("firstName") + "; "
                        + resultSet.getString("address") + ";");
            }
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void reflectionPrint(){
        Client client = new Client(1, "Alexandru", "Fărcaș", "Pop Reteganu 10");
        Reflection.objectPrint(client);
    }

    @Test
    public void printClient(){
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.findById(1);
        Reflection.objectPrint(client);
    }

    @Test
    public void printOrder(){
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.findById(1);
        Reflection.objectPrint(order);
    }

    @Test
    public void printProduct(){
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findById(1);
        Reflection.objectPrint(product);
    }

    @Test
    public void updateClient(){
        Client client = new Client(1, "Alexandru", "Fărcaș", "Pop Reteganu 10");
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.update(client);
        Client result = clientDAO.findById(1);
        Reflection.objectPrint(result);
    }

    @Test
    public void insertTest(){
        Client client = new Client(1, "Alexandru", "Fărcaș", "Pop Reteganu 10");
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.insert(client);
        ProductDAO productDAO = new ProductDAO();
        productDAO.insert(new Product(1, "test", "test", 100));
    }

    @Test
    public void filterByIdTest(){
        ClientDAO clientDAO = new ClientDAO();
        List<Client> clientList = clientDAO.filterById("2");
        for (Client i : clientList){
            Reflection.objectPrint(i);
        }
    }
}
