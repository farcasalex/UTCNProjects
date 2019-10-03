//Created by Farcas Alexandru
//UTCN 2019
//22/04/2019
package dataAccessLayer;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class ProductDAO extends AbstractDAO<Product>{

    @Override
    public String createSelectQuery(String field){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append("* ");
        stringBuilder.append("FROM ");
        stringBuilder.append("order_management.product ");
        stringBuilder.append("WHERE " + field + " = ?");
        return stringBuilder.toString();
    }

    @Override
    public void update(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "UPDATE order_management.product\n" +
                "SET category = ?, productName = ?, stock = ?\n" +
                "WHERE id = ?";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getCategory());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "product DAO:update" + e.getMessage());
        }finally {
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
    }

    public void insert(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO order_management.product (category, productName, stock) VALUES (\"" + product.getCategory() + "\", \""
                + product.getProductName() + "\", \"" + product.getStock() + "\")";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, this.getClass().getName() + " DAO:insert" + e.getMessage());
        }finally {
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
    }

    public List<Product> filterById(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM order_management.product" + " WHERE product.id like \'" + string + "%\'";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, this.getClass().getName() + ":filterById" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

    public List<Product> filterByProductName(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM order_management.product" + " WHERE product.productName like \'" + string + "%\'";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, this.getClass().getName() + ":filterByProductName" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

    public List<Product> filterByCategory(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM order_management.product" + " WHERE product.category like \'" + string + "%\'";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, this.getClass().getName() + ":filterByCategory" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

    public List<Product> filterByStock(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM order_management.product" + " WHERE product.stock like \'" + string + "%\'";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, this.getClass().getName() + ":filterByStock" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

}
