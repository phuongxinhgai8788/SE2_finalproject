package app.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inventories",
       schema = "scm",
       catalog = "")
public class Inventory {
    private int id;
    private String name;
    private Double price;
    private String source;
    private String thumbnailUrl;
    private String tags;

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
            length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price",
            nullable = true,
            precision = 0)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "source",
            nullable = true,
            length = 1024)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Basic
    @Column(name = "thumbnailUrl",
            nullable = true,
            length = 1024)
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Basic
    @Column(name = "tags",
            nullable = true,
            length = 1024)
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return id == inventory.id &&
            Objects.equals(name, inventory.name) &&
            Objects.equals(price, inventory.price) &&
            Objects.equals(source, inventory.source) &&
            Objects.equals(thumbnailUrl, inventory.thumbnailUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, source, thumbnailUrl);
    }
}
