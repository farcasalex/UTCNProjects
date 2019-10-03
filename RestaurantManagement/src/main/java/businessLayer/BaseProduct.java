//Created by Farcas Alexandru
//UTCN 2019
//16/05/2019
package businessLayer;

import java.io.Serializable;

public class BaseProduct implements MenuItem, Serializable {

    private int price;
    private String name;

    public BaseProduct() {
    }

    public BaseProduct(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int computePrice() {
        return price;
    }
}
