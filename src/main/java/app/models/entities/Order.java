package app.models.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orders",
       schema = "dhawuidhi",
       catalog = "")
public class Order {
    private int id = -1;
    private String customerName;
    private String address;
    private String phoneNumber;
    private int transportingUnitId;
    private int transporterId;
    private String notes;
    private Timestamp createdDate;
    private String status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customerName",
            nullable = false,
            length = 256)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "address",
            nullable = false,
            length = 1024)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "phoneNumber",
            nullable = false,
            length = 20)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "transportingUnitId",
            nullable = false)
    public int getTransportingUnitId() {
        return transportingUnitId;
    }

    public void setTransportingUnitId(int transportingUnitId) {
        this.transportingUnitId = transportingUnitId;
    }

    @Basic
    @Column(name = "transporterId",
            nullable = false)
    public int getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(int transporterId) {
        this.transporterId = transporterId;
    }

    @Basic
    @Column(name = "notes",
            nullable = true,
            length = 256)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "createdDate",
            nullable = false)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "status",
            nullable = true,
            length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
            transportingUnitId == order.transportingUnitId &&
            transporterId == order.transporterId &&
            Objects.equals(customerName, order.customerName) &&
            Objects.equals(address, order.address) &&
            Objects.equals(phoneNumber, order.phoneNumber) &&
            Objects.equals(notes, order.notes) &&
            Objects.equals(createdDate, order.createdDate) &&
            Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                            customerName,
                            address,
                            phoneNumber,
                            transportingUnitId,
                            transporterId,
                            notes,
                            createdDate,
                            status);
    }
}
