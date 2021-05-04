package app.models.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoleDao {
    @Id
    private int id;
    private String name;
    private int laborsNum;

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

    public int getLaborsNum() {
        return laborsNum;
    }

    public void setLaborsNum(int laborsNum) {
        this.laborsNum = laborsNum;
    }
}
