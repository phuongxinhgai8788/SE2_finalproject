package app.utils;

import app.models.entities.Inventory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryUtil extends Util<Inventory> {
    public InventoryUtil() {
        super("inventories", Inventory.class);
    }

    public List<Inventory> getByTags(List<String> tags) {
        return getAll()
            .stream()
            .filter(s -> Arrays.stream(s.getTags().split(","))
                .anyMatch(tags::contains)).limit(3)
            .collect(Collectors.toList());
    }
}
