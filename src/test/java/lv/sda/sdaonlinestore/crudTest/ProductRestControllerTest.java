package lv.sda.sdaonlinestore.crudTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import lv.sda.sdaonlinestore.entity.Author;
import lv.sda.sdaonlinestore.entity.Category;
import lv.sda.sdaonlinestore.entity.Product;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc

public class ProductRestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;

    @Test
    public void testProductFullCrud() throws Exception {

        Category testCategory = new Category();
        testCategory.setChildName("Female Outfit");
        testCategory.setParentName("Outfit");
        testCategory.setName("Female shoes");

        Author testAuthor = new Author();
        testAuthor.setFirstName("First");
        testAuthor.setLastName("Last");

        Product testProduct = new Product();
        testProduct.setAuthor(testAuthor);
        testProduct.setProductType("Fashion");
        testProduct.setThumbnail("");
        testProduct.setPrice(BigDecimal.valueOf(50));
        testProduct.setDescription("Shoes fo females");
        testProduct.setCategory(testCategory);


        String productJson = mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .content(mapper.writeValueAsString(testProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Product> createdProductList = (List<Product>) mapper.readValue(productJson, List.class);
        Product createdProduct = mapper.convertValue(createdProductList.get(0), Product.class);

        createdProduct.setProductType("Women's Shoes");

        mockMvc.perform(MockMvcRequestBuilders.put("/products")
                .content(mapper.writeValueAsString(createdProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String tt = mockMvc.perform(MockMvcRequestBuilders.get("/products/byId").param("productId", createdProduct.getProductId().toString()))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        List<Product> createdProductlist1 = (List<Product>) mapper.readValue(tt, List.class);
        Product createdProduct1 = mapper.convertValue(createdProductlist1.get(0), Product.class);

        assertTrue(createdProduct1.getProductType().equals("Women's Shoes"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/products").content(tt).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/products/byId").param("productId", createdProduct1.getProductId().toString()))
                .andExpect(status().isNotFound());


    }

}
