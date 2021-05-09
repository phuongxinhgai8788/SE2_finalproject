import app.models.entities.Warehouse;
import app.utils.WarehouseUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WarehousesTest {
    private WarehouseUtil warehouseUtil;

    @BeforeAll
    public void init() {
        warehouseUtil = new WarehouseUtil();
    }

    @Test
    void creation() {
        var warehouse = new Warehouse();
        warehouse.setId(99);
        warehouse.setAddress("Asd");
        warehouse.setManagerId(1);
        var newWh = warehouseUtil.create(warehouse);
        assertEquals(warehouse, newWh);
    }

    @Test
    void modification() {
        var wh = warehouseUtil.getById(99);
        wh.setAddress("2d09dj1209j");
        var modifiedWh = warehouseUtil.update(wh);
        assertEquals(wh, modifiedWh);
    }
}
