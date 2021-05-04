package app.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderDetailDto {
    @NotNull
    @Min(1)
    private int itemId;
    @NotNull
    @Min(1)
    private double itemPrice;
    @NotNull
    @Min(1)
    private int itemQty;

    public OrderDetailDto(int itemId, double itemPrice, int itemQty) {
        this.itemId = itemId;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
    }

    public int getItemId() {
        return itemId;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getItemQty() {
        return itemQty;
    }
}
