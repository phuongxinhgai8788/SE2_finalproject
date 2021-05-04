package app.models.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderDetailDao {
    @Id
    private int itemId;
    private String name;
    private String source;
    private String thumbnailUrl;
    private double price;
    private int quantity;

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
