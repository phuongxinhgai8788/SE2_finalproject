package app.models.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class OrderDeliveryDetailDto {
    @NotNull
    @NotBlank
    private String notes;

    private Timestamp updatedDate;

    public OrderDeliveryDetailDto(String notes) {
        this.notes = notes;
        updatedDate = new Timestamp(System.currentTimeMillis());
    }

    public String getNotes() {
        return notes;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }
}
