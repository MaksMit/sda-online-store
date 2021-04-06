package lv.sda.sdaonlinestore.crudTest;

import lv.sda.sdaonlinestore.entity.StoreUser;
import lv.sda.sdaonlinestore.service.StoreUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreUserCrudTest {
    @Autowired
    StoreUserService storeUserService;
    @Test
    public void testCreate() {
        StoreUser testStoreUser = new StoreUser();
        testStoreUser.setLogin("");
        testStoreUser.setPassword("");
        //testStoreUser.setCity();
        testStoreUser.setAddress("");
        testStoreUser.setAvatar("");
        testStoreUser.setRole("");
        testStoreUser.setEmail("");
        testStoreUser.setPhone("");
        storeUserService.save(testStoreUser);
    }

    @Test
    public void testUpdate() {
        StoreUser testStoreUser = new StoreUser();
        testStoreUser.setLogin("");
        testStoreUser.setPassword("");
        //testStoreUser.setCity();
        testStoreUser.setAddress("");
        testStoreUser.setAvatar("");
        testStoreUser.setRole("");
        testStoreUser.setEmail("");
        testStoreUser.setPhone("");
        storeUserService.saveOrUpdate(testStoreUser);
    }

    @Test
    public void testGet() {
        storeUserService.getAllStoreUsers();

    }

}
