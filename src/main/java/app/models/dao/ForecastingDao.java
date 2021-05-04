package app.models.dao;

import app.helpers.CompositeKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Date;

@Entity
@IdClass(CompositeKey.class)
public class ForecastingDao {
    @Id
    private int id;
    private String name;
    private String tags;
    @Id
    private Date createdDate;
    private int sum;

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String[] splitTaggy() {
        return tags.split(",");
    }
}
