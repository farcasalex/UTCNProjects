//Created by Farcas Alexandru
//UTCN 2019
//16/05/2019
package businessLayer;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct implements MenuItem{

    private int price;
    private String name;
    private ArrayList<BaseProduct> baseProductList = new ArrayList<BaseProduct>();

    public CompositeProduct(String name, ArrayList<BaseProduct> baseProductList) {
        this.name = name;
        this.baseProductList = baseProductList;
        this.price = computePrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBaseProduct(BaseProduct baseProduct){
        baseProductList.add(baseProduct);
    }

    public void removeBaseProduct(BaseProduct baseProduct){
        baseProductList.remove(baseProduct);
    }

    public int getPrice() {
        return computePrice();
    }

    public ArrayList<BaseProduct> getBaseProductList() {
        return baseProductList;
    }

    public void setBaseProductList(ArrayList<BaseProduct> baseProductList) {
        this.baseProductList = baseProductList;
    }

    @Override
    public int computePrice() {
        int price = 0;
        for (BaseProduct product: baseProductList) {
            price += product.computePrice();
        }
        this.price = price;
        return price;
    }
}
