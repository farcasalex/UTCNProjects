//Created by Farcas Alexandru
//UTCN 2019
//21/04/2019
package model;

public class Order {
    private int id;
    private  String firstName;
    private String name;
    private String category;
    private String productName;
    private int amount;

    public Order(int id, String firstName, String name, String category, String productName, int amount) {
        this.id = id;
        this.firstName = firstName;
        this.name = name;
        this.category = category;
        this.productName = productName;
        this.amount = amount;
    }

    public Order(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
