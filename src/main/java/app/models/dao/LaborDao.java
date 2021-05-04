package app.models.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LaborDao {
    @Id
    private int id;
    private String name;
    private String phoneNumber;
    private String role;
    private String transportingUnit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTransportingUnit() {
        return transportingUnit;
    }

    public void setTransportingUnit(String transportingUnit) {
        this.transportingUnit = transportingUnit;
    }
}
