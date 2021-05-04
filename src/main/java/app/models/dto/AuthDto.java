package app.models.dto;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthDto {
    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 1, max = 256)
    private String password;

    public AuthDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
