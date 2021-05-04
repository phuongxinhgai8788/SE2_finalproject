package app.models.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Date;

public class ProfileUpdateDto {
    @NotNull
    @NotBlank
    private String name;

    private Date dateOfBirth;

    @NotNull
    @NotBlank
    private String phoneNumber;

    @Valid
    private PasswordDto pw;

    public ProfileUpdateDto(
        String name,
        Date dateOfBirth,
        String phoneNumber,
        String password,
        String passwordConfirm) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;

        if (password != null && !password.isEmpty())
            this.pw = new PasswordDto(password, passwordConfirm);
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PasswordDto getPw() {
        return pw;
    }
}