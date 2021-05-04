package app.models.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class PasswordDto {
    @NotNull
    @NotBlank
    @Max(256)
    private String password;

    @NotNull
    @NotBlank
    @Max(256)
    private String passwordConfirm;

    @AssertTrue(message = "password mismatches")
    private boolean isValid() {
        return this.passwordConfirm.equals(this.password);
    }

    public PasswordDto(String password, String passwordConfirm) {
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }
}
