package app.models.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderDto {
    @NotNull
    @NotBlank
    private String customerName;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @NotBlank
    private String phoneNumber;

    @NotNull
    @Min(1)
    private int transportingUnitId;

    @NotNull
    @Min(1)
    private int transporterId;

    private String notes;

    @NotNull
    private String status;

    @Valid
    private OrderDeliveryDetailDto deliveryDetail;

    public OrderDto(
        String customerName,
        String address,
        String phoneNumber,
        int transportingUnitId,
        int transporterId,
        String notes,
        String status) {
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.transportingUnitId = transportingUnitId;
        this.transporterId = transporterId;
        this.notes = notes;
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getTransportingUnitId() {
        return transportingUnitId;
    }

    public int getTransporterId() {
        return transporterId;
    }

    public String getNotes() {
        return notes;
    }

    public String getStatus() {
        return status;
    }

    public OrderDeliveryDetailDto getDeliveryDetail() {
        return deliveryDetail;
    }

    public void setDeliveryDetail(OrderDeliveryDetailDto deliveryDetail) {
        this.deliveryDetail = deliveryDetail;
    }
}
