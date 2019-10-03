//Created by Farcas Alexandru
//UTCN 2019
//17/05/2019

import businessLayer.*;
import dataLayer.RestaurantSerializator;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

public class JUnitTests {

    @Test
    public void orderTest(){
        Order order1 = new Order(1, 1);
        Order order2 = new Order(2, 1);
        System.out.println(order1.equals(order2));
    }

    @Test
    public void restaurantTest(){
        Restaurant restaurant = new Restaurant();

        MenuItem menuItem1 = new BaseProduct(9,"Bread");
        MenuItem menuItem2 = new BaseProduct(4,"Soup");
        MenuItem menuItem3 = new BaseProduct(6,"Chicken");
        MenuItem menuItem4 = new BaseProduct(12,"Fish");
        MenuItem menuItem5 = new BaseProduct(5,"Salad");

        ArrayList<MenuItem> orderedProducts = new ArrayList<>();
        orderedProducts.add(menuItem1);
        orderedProducts.add(menuItem2);
        orderedProducts.add(menuItem3);
        orderedProducts.add(menuItem4);
        orderedProducts.add(menuItem5);

        Order order1 = new Order(1, 1);
        Order order2 = new Order(2, 1);
        Order order3 = new Order(3, 1);

        restaurant.getOrders().putIfAbsent(order1, orderedProducts);
        restaurant.getOrders().putIfAbsent(order2, orderedProducts);
        restaurant.getOrders().putIfAbsent(order3, orderedProducts);

        restaurant.getOrders().forEach((order, menuItems) ->{
            System.out.println("Id: " + order.getOrderId() + ", date: " + order.getDate());
            for (MenuItem item: menuItems){
                System.out.println("Price: " + item.computePrice());
            }
        });

        System.out.println("Saving. . .");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("restaurant.bit");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(restaurant);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Reloading. . .");

        Restaurant restaurant1 = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("restaurant.bit");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            restaurant1 = (Restaurant) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        restaurant1.getOrders().forEach((order, menuItems) ->{
            System.out.println("Id: " + order.getOrderId() + ", date: " + order.getDate());
            for (MenuItem item: menuItems){
                System.out.println("Price: " + item.computePrice());
            }
        });
    }

    @Test
    public void populateRestaurant(){
        Restaurant restaurant = new Restaurant();

        MenuItem menuItem1 = new BaseProduct(9,"Bread");
        MenuItem menuItem2 = new BaseProduct(4,"Soup");
        MenuItem menuItem3 = new BaseProduct(6,"Chicken");
        MenuItem menuItem4 = new BaseProduct(12,"Fish");
        MenuItem menuItem5 = new BaseProduct(5,"Salad");

        ArrayList<MenuItem> products = new ArrayList<>();
        products.add(menuItem1);
        products.add(menuItem2);
        products.add(menuItem3);
        products.add(menuItem4);
        products.add(menuItem5);
        restaurant.setProducts(products);

        ArrayList<BaseProduct> list = new ArrayList<>();
        list.add((BaseProduct) menuItem1);
        list.add((BaseProduct) menuItem2);
        MenuItem compositeProduct = new CompositeProduct("Secret Dish", list);
        compositeProduct.computePrice();
        products.add(compositeProduct);

        ArrayList<BaseProduct> list1 = new ArrayList<>();
        list1.add((BaseProduct) menuItem4);
        list1.add((BaseProduct) menuItem5);
        MenuItem compositeProduct1 = new CompositeProduct("Fish Plate", list);
        compositeProduct1.computePrice();
        products.add(compositeProduct1);

        ArrayList<BaseProduct> list2 = new ArrayList<>();
        list2.add((BaseProduct) menuItem1);
        list2.add((BaseProduct) menuItem2);
        list2.add((BaseProduct) menuItem3);
        MenuItem compositeProduct2 = new CompositeProduct("Menu of the day", list);
        compositeProduct2.computePrice();
        products.add(compositeProduct2);

        ArrayList<MenuItem> ordered = new ArrayList<>();
        ordered.add(compositeProduct);
        ordered.add(menuItem5);
        restaurant.getOrders().putIfAbsent(new Order(1, 1), ordered);

        RestaurantSerializator serializator = new RestaurantSerializator();
        serializator.save(restaurant);
    }
}
