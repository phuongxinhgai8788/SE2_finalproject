package app.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "warehouseItems",
       schema = "dhawuidhi",
       catalog = "")
@IdClass(WarehouseItemPK.class)
public class WarehouseItem {
    private int inventoryId;
    private int warehouseId;
    private int quantity;

    @Id
    @Column(name = "inventoryId",
            nullable = false)
    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    @Id
    @Column(name = "warehouseId",
            nullable = false)
    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
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
        WarehouseItem that = (WarehouseItem) o;
        return inventoryId == that.inventoryId &&
            warehouseId == that.warehouseId &&
            quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, warehouseId, quantity);
    }
}
