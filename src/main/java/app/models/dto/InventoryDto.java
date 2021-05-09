package app.models.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class InventoryDto {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @Min(20)
    private Double price;
    private String source;
    private String thumbnailUrl;
    private String tags;

    public InventoryDto(String name, Double price, String source, String thumbnailUrl, String tags) {
        this.name = name;
        this.price = price;
        this.source = source;
        this.thumbnailUrl = thumbnailUrl;
        this.tags = tags;
    }


    public String getName() {
        return name;
    }


    public Double getPrice() {
        return price;
    }


    public String getSource() {
        return source;
    }


    public String getThumbnailUrl() {
        return thumbnailUrl;
    }


    public String getTags() {
        return tags;
    }

}
