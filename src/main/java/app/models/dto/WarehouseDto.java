package app.models.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDto {
    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @Min(1)
    private int managerId;

    @Valid
    private List<WarehouseItemDto> items;

    public WarehouseDto(String address, int managerId) {
        this.address = address;
        this.managerId = managerId;

        items = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public int getManagerId() {
        return managerId;
    }

    public List<WarehouseItemDto> getItems() {
        return items;
    }

    public void setItems(List<WarehouseItemDto> items) {
        this.items = items;
    }
}
