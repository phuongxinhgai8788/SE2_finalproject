package app.models.dto;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class OrderCreateDto extends OrderDto {
    @Valid
    private List<OrderDetailDto> items;

    public OrderCreateDto(
        String customerName,
        String address,
        String phoneNumber,
        int transportingUnitId,
        int transporterId,
        String notes, String status) {
        super(customerName,
              address,
              phoneNumber,
              transportingUnitId,
              transporterId,
              notes,
              status);
        items = new ArrayList<>();
    }

    public List<OrderDetailDto> getItems() {
        return items;
    }

    public void setItems(List<OrderDetailDto> items) {
        this.items = items;
    }
}
