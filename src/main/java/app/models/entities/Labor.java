package app.models.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "labors",
       schema = "scm",
       catalog = "")
public class Labor {
    private int id;
    private String name;
    private Date dateOfBirth;
    private String phoneNumber;
    private Integer roleId;
    private Integer transportingUnitId;
    private String password;
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name",
            nullable = false,
            length = 256)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "dateOfBirth",
            nullable = false)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = "phoneNumber",
            nullable = false,
            length = 20)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "roleId")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "transportingUnitId")
    public Integer getTransportingUnitId() {
        return transportingUnitId;
    }

    public void setTransportingUnitId(Integer transportingUnitId) {
        this.transportingUnitId = transportingUnitId;
    }

    @Basic
    @Column(name = "password",
            length = 256)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email",
            nullable = false,
            length = 256)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Labor labor = (Labor) o;
        return id == labor.id &&
            roleId == labor.roleId &&
            transportingUnitId == labor.transportingUnitId &&
            Objects.equals(name, labor.name) &&
            Objects.equals(dateOfBirth, labor.dateOfBirth) &&
            Objects.equals(phoneNumber, labor.phoneNumber) &&
            Objects.equals(password, labor.password) &&
            Objects.equals(email, labor.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                            name,
                            dateOfBirth,
                            phoneNumber,
                            roleId,
                            transportingUnitId,
                            password,
                            email);
    }
}
