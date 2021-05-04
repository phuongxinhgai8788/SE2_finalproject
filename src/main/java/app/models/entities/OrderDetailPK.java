package app.models.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrderDetailPK implements Serializable {
    private int orderId;
    private int itemId;

    @Column(name = "orderId",
            nullable = false)
    @Id
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(name = "itemId",
            nullable = false)
    @Id
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailPK that = (OrderDetailPK) o;
        return orderId == that.orderId &&
            itemId == that.itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, itemId);
    }
}
