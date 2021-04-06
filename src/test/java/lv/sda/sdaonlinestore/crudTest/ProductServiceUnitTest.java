package lv.sda.sdaonlinestore.crudTest;

import lv.sda.sdaonlinestore.entity.Product;
import lv.sda.sdaonlinestore.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceUnitTest {

    @Autowired
    private ProductService productService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<Product> products = productService.getAllProducts();

        Assert.assertEquals(0,products.size());
    }
}
