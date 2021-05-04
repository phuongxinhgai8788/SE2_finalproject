package app.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orderDetails",
       schema = "dhawuidhi",
       catalog = "")
@IdClass(OrderDetailPK.class)
public class OrderDetail {
    private int orderId;
    private int itemId;
    private double price;
    private int quantity;

    @Id
    @Column(name = "orderId",
            nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "itemId",
            nullable = false)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "price",
            nullable = false,
            precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "quantity",
            nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return orderId == that.orderId &&
            itemId == that.itemId &&
            Double.compare(that.price, price) == 0 &&
            quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, itemId, price, quantity);
    }
}
