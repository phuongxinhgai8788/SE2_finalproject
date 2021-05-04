package app.models.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class TransportingUnitDto {
    @NotNull
    @NotBlank
    private String name;

    public TransportingUnitDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
