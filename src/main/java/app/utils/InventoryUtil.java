package app.utils;

import app.models.dao.InventoryDao;
import app.models.dao.LaborDao;
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
    public List<InventoryDao> getAllWithFullAttributes() {
        var query = String.format(
                "SELECT i.id, i.name, i.price, i.source, i.thumbnailUrl, i.tags\n" +
                        "    FROM %s i\n",
                tableName);
        try (var session = HibernateUtil.getSession()) {
            return session.createNativeQuery(query, InventoryDao.class)
                    .getResultList();
        }
    }

}
