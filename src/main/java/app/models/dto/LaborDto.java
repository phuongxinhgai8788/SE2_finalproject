package app.models.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class LaborDto {
    @NotNull
    @NotEmpty
    private String name;
    private Date dateOfBirth;
    private String phoneNumber;
    private int roleId;
    private int transportingUnitId;
    private String password;
    private String email;

    public LaborDto(
        String name,
        Date dateOfBirth,
        String phoneNumber,
        int roleId,
        int transportingUnitId,
        String password,
        String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.roleId = roleId;
        this.transportingUnitId = transportingUnitId;
        this.password = password;
        this.email = email;
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

    public int getRoleId() {
        return roleId;
    }

    public int getTransportingUnitId() {
        return transportingUnitId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
