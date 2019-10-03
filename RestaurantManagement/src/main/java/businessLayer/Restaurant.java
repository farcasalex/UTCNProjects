//Created by Farcas Alexandru
//UTCN 2019
//16/05/2019
package businessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class Restaurant extends Observable implements Serializable {
    private HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<>();
    private ArrayList<MenuItem> products = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(HashMap<Order, ArrayList<MenuItem>> orders, ArrayList<MenuItem> products) {
        this.orders = orders;
        this.products = products;
    }

    public HashMap<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<Order, ArrayList<MenuItem>> orders) {
        this.orders = orders;
    }

    public ArrayList<MenuItem> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<MenuItem> products) {
        this.products = products;
    }

    public Order getOrderById(int id){
        final Order[] result = {new Order()};
        orders.forEach((order, menuItems) -> {
            if (order.getOrderId() == id){
                result[0] = order;
            }
        });
        return result[0];
    }

    public Order getOrderByTable(int table){
        final Order[] result = {new Order()};
        orders.forEach((order, menuItems) -> {
            if (order.getTable() == table){
                result[0] = order;
            }
        });
        return result[0];
    }

    public CompositeProduct getProductByName(String name){
        for (MenuItem item : products){
            if (item instanceof CompositeProduct && ((CompositeProduct) item).getName() == name){
                return (CompositeProduct) item;
            }
        }
        return null;
    }
}
