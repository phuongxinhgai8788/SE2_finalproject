package app.models.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class WarehouseItemPK implements Serializable {
    private int inventoryId;
    private int warehouseId;

    @Column(name = "inventoryId",
            nullable = false)
    @Id
    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    @Column(name = "warehouseId",
            nullable = false)
    @Id
    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseItemPK that = (WarehouseItemPK) o;
        return inventoryId == that.inventoryId &&
            warehouseId == that.warehouseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, warehouseId);
    }
}
