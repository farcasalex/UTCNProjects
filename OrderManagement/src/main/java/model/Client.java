//Created by Farcas Alexandru
//UTCN 2019
//21/04/2019
package model;

public class Client {
    private int id;
    private String firstName;
    private String name;
    private String address;

    public Client() {

    }

    public Client(int id, String firstName, String name, String address) {
        this.id = id;
        this.firstName = firstName;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
