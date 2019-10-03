//Created by Farcas Alexandru
//UTCN 2019
//16/05/2019
package businessLayer;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Serializable {

    private int orderId;
    private String date;
    private int table;

    public Order() {
    }

    public Order(int orderId, int table) {
        this.orderId = orderId;
        this.date = LocalDateTime.now().getDayOfMonth() + "/" + LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getYear()
                    + " " + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute();
        this.table = table;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Order))
            return false;
        if (obj == this)
            return true;
        return this.getOrderId() == ((Order) obj).getOrderId() && this.getTable() == ((Order) obj).getTable() && this.getDate().equals(((Order) obj).getDate());
    }

    @Override
    public int hashCode(){
        return orderId;
    }
}
