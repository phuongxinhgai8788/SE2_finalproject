package app.models.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orderDeliveryDetails",
       schema = "scm",
       catalog = "")
@IdClass(OrderDeliveryDetailPK.class)
public class OrderDeliveryDetail {
    private int orderId;
    private Timestamp updatedDate;
    private String notes;

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
    @Column(name = "updatedDate",
            nullable = false)
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Basic
    @Column(name = "notes",
            nullable = false,
            length = 1024)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDeliveryDetail that = (OrderDeliveryDetail) o;
        return orderId == that.orderId &&
            Objects.equals(updatedDate, that.updatedDate) &&
            Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, updatedDate, notes);
    }
}
