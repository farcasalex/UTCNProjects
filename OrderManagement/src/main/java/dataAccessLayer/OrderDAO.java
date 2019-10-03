//Created by Farcas Alexandru
//UTCN 2019
//22/04/2019
package dataAccessLayer;

import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class OrderDAO extends AbstractDAO<Order> {

    @Override
    public String createSelectQuery(String field){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT order_management.order.id, order_management.client.firstName, " +
                "order_management.client.name, order_management.product.category, order_management.product.productName," +
                " order_management.order.amount\n" + "FROM order_management.product, order_management.client, " +
                "order_management.order\n" + "WHERE order_management.order.productId = order_management.product.id AND " +
                "order_management.order.clientId = order_management.client.id AND ");
        stringBuilder.append("order_management.order." + field + " = ?");
        return stringBuilder.toString();
    }

    @Override
    public List<Order> findAll(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT order_management.order.id, order_management.client.firstName, " +
                "order_management.client.name, order_management.product.category, order_management.product.productName," +
                " order_management.order.amount\n" + "FROM order_management.product, order_management.client, " +
                "order_management.order\n" + "WHERE order_management.order.productId = order_management.product.id AND " +
                "order_management.order.clientId = order_management.client.id";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "order DAO:findAll" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

    @Override
    public void update(Order object) {
        // TODO: not required
    }

    public void insert(int productId, int amount, int clientId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO order_management.order (productId, amount, clientId) VALUES (" + productId + ", "
                + amount + ", " + clientId + ")";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute(query);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, this.getClass().getName() + " DAO:insert " + e.getMessage());
        }finally {
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
    }

    public List<Order> filterById(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT order_management.order.id, order_management.client.firstName, " +
                "order_management.client.name, order_management.product.category, order_management.product.productName," +
                " order_management.order.amount\n" + "FROM order_management.product, order_management.client, " +
                "order_management.order\n" + "WHERE order_management.order.productId = order_management.product.id AND " +
                "order_management.order.clientId = order_management.client.id AND order_management.order.id LIKE \'" + string + "%\'";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "order DAO:filterById" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

    public List<Order> filterByFirstName(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT order_management.order.id, order_management.client.firstName, " +
                "order_management.client.name, order_management.product.category, order_management.product.productName," +
                " order_management.order.amount\n" + "FROM order_management.product, order_management.client, " +
                "order_management.order\n" + "WHERE order_management.order.productId = order_management.product.id AND " +
                "order_management.order.clientId = order_management.client.id AND order_management.client.firstName LIKE \'" + string + "%\'";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "order DAO:filterByFirstName" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

    public List<Order> filterByName(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT order_management.order.id, order_management.client.firstName, " +
                "order_management.client.name, order_management.product.category, order_management.product.productName," +
                " order_management.order.amount\n" + "FROM order_management.product, order_management.client, " +
                "order_management.order\n" + "WHERE order_management.order.productId = order_management.product.id AND " +
                "order_management.order.clientId = order_management.client.id AND order_management.client.name LIKE \'" + string + "%\'";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "order DAO:filterByName" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

    public List<Order> filterByCategory(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT order_management.order.id, order_management.client.firstName, " +
                "order_management.client.name, order_management.product.category, order_management.product.productName," +
                " order_management.order.amount\n" + "FROM order_management.product, order_management.client, " +
                "order_management.order\n" + "WHERE order_management.order.productId = order_management.product.id AND " +
                "order_management.order.clientId = order_management.client.id AND order_management.product.category LIKE \'" + string + "%\'";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "order DAO:filterByCategory" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

    public List<Order> filterByProductName(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT order_management.order.id, order_management.client.firstName, " +
                "order_management.client.name, order_management.product.category, order_management.product.productName," +
                " order_management.order.amount\n" + "FROM order_management.product, order_management.client, " +
                "order_management.order\n" + "WHERE order_management.order.productId = order_management.product.id AND " +
                "order_management.order.clientId = order_management.client.id AND order_management.product.name LIKE \'" + string + "%\'";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "order DAO:filterByProductName" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

    public List<Order> filterByAmount(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT order_management.order.id, order_management.client.firstName, " +
                "order_management.client.name, order_management.product.category, order_management.product.productName," +
                " order_management.order.amount\n" + "FROM order_management.product, order_management.client, " +
                "order_management.order\n" + "WHERE order_management.order.productId = order_management.product.id AND " +
                "order_management.order.clientId = order_management.client.id AND order_management.order.amount LIKE \'" + string + "%\'";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "order DAO:filterByAmount" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }
}
