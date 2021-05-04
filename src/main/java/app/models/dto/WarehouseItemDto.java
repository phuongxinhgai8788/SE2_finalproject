package app.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class WarehouseItemDto {
    @NotNull
    @Min(1)
    private int inventoryId;

    @NotNull
    @Min(1)
    private int quantity;

    public WarehouseItemDto(int inventoryId, int quantity) {
        this.inventoryId = inventoryId;
        this.quantity = quantity;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public int getQuantity() {
        return quantity;
    }
}
