//Created by Farcas Alexandru
//UTCN 2019
//22/04/2019
package dataAccessLayer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDAO<T> {
    protected final static Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    public AbstractDAO(){
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public abstract String createSelectQuery(String field);

    public T findById(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("Id");
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet).get(0);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, type.getSimpleName() + "DAO:findById" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

    protected List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        try{
            while (resultSet.next()){
                T instance = type.newInstance();
                for(Field field : type.getDeclaredFields()){
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName().toLowerCase();
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return  createObjects(resultSet);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, type.getSimpleName() + "DAO:findAll" + e.getMessage());
        }finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
        return null;
    }

    public abstract void update(T object);

    public void delete(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM order_management." + type.getSimpleName().toLowerCase() + " WHERE id = " + id;
        try{
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, type.getSimpleName() + " DAO:update" + e.getMessage());
        }finally {
            DatabaseConnection.close(preparedStatement);
            DatabaseConnection.close(connection);
        }
    }
}
