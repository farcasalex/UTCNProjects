//Created by Farcas Alexandru
//UTCN 2019
//19/05/2019
package dataLayer;

import businessLayer.*;

import java.io.*;

public class RestaurantSerializator {

    public void save(Restaurant restaurant){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("restaurant.bit");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(restaurant);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Restaurant load(){
        Restaurant restaurant = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("restaurant.bit");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            restaurant = (Restaurant) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(restaurant == null){
            return new Restaurant();
        }
        return restaurant;
    }

}
