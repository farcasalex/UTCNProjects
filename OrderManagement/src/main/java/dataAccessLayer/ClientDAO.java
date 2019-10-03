//Created by Farcas Alexandru
//UTCN 2019
//22/04/2019
package dataAccessLayer;

import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class ClientDAO extends AbstractDAO<Client> {

    @Override
    public String createSelectQuery(String field){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append("* ");
        stringBuilder.append("FROM ");
        stringBuilder.append("order_management.client ");
        stringBuilder.append("WHERE " + field + " = ?");
        return stringBuilder.toString();
    }

    @Override
    public void update(Client client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "UPDATE order_management.client\n" +
                "SET firstName = ?, name = ?, address = ?\n" +
                "WHERE id = ?";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getAddress());
            preparedStatement.setInt(4, client.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "client DAO:update" + e.getMessage());
        }finally {
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
    }

    public void insert(Client client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO order_management.client (firstName, name, address) VALUES (\"" + client.getFirstName() + "\", \""
                        + client.getName() + "\", \"" + client.getAddress() + "\")";
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

    public List<Client> filterById(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM order_management.client" + " WHERE client.id like \'" + string + "%\'";
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

    public List<Client> filterByName(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM order_management.client" + " WHERE client.name like \'" + string + "%\'";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, this.getClass().getName() + ":filterByName" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

    public List<Client> filterByFirstName(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM order_management.client" + " WHERE client.firstName like \'" + string + "%\'";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, this.getClass().getName() + ":filterByFirstName" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

    public List<Client> filterByAddress(String string){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM order_management.client" + " WHERE client.address like \'" + string + "%\'";
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, this.getClass().getName() + ":filterByAddress" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }
}
