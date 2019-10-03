//Created by Farcas Alexandru
//UTCN 2019
//21/04/2019
package model;

public class Product {
    private int id;
    private String category;
    private String productName;
    private int stock;

    public Product(int id, String category, String productName, int stock) {
        this.id = id;
        this.category = category;
        this.productName = productName;
        this.stock = stock;
    }

    public Product(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
