package app.models.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class OrderDeliveryDetailPK implements Serializable {
    private int orderId;
    private Timestamp updatedDate;

    @Column(name = "orderId",
            nullable = false)
    @Id
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(name = "updatedDate",
            nullable = false)
    @Id
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDeliveryDetailPK that = (OrderDeliveryDetailPK) o;
        return orderId == that.orderId &&
            Objects.equals(updatedDate, that.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, updatedDate);
    }
}
