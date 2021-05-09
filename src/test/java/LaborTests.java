import app.models.entities.Labor;
import app.utils.LaborUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaborTests {
    private LaborUtil laborUtil;

    @BeforeAll
    public void init() {
        laborUtil = new LaborUtil();
    }

    @Test
    void creation() {
        var labor = new Labor();
        labor.setId(99);
        labor.setEmail("Asd@gmaill.com");
        labor.setTransportingUnitId(1);
        labor.setName("Abc");
        labor.setPassword("2");
        labor.setPhoneNumber("01234454");
        var newLabor = laborUtil.create(labor);
        assertEquals(newLabor, labor);
    }

    @Test
    void modification() {
        var labor = laborUtil.getById(99);
        labor.setPhoneNumber("123");
        var mLabor = laborUtil.update(labor);
        assertEquals(mLabor, labor);
    }
}