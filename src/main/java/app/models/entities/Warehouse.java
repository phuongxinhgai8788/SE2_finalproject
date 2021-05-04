package app.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "warehouses",
       schema = "dhawuidhi",
       catalog = "")
public class Warehouse {
    private int id;
    private String address;
    private Integer managerId;

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
    @Column(name = "managerId")
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return id == warehouse.id &&
            managerId == warehouse.managerId &&
            Objects.equals(address, warehouse.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, managerId);
    }
}
