package app.models.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoleDto {
    @NotNull
    @NotBlank
    @Size(max = 256)
    private String name;

    public RoleDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
