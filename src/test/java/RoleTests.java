import app.models.entities.Role;
import app.utils.RoleUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTests {
    private RoleUtil roleUtil;

    @BeforeAll
    public void init() {
        roleUtil = new RoleUtil();
    }

    @Test
    void creation() {
        var role = new Role();
        role.setId(9);
        role.setName("A");
        var newRole = roleUtil.create(role);
        assertEquals(newRole, role);
    }

    @Test
    void modification() {
        var role = roleUtil.getById(9);
        role.setName("Hoa");
        var mRole = roleUtil.update(role);
        assertEquals(mRole, role);
    }
}