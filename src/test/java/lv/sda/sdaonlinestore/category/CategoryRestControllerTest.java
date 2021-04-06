package lv.sda.sdaonlinestore.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.sda.sdaonlinestore.entity.Category;
import lv.sda.sdaonlinestore.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class CategoryRestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;

    @Test
    public void testOrderFullCrud() throws Exception {

        Category testCategory = new Category();
        testCategory.setName("Fashion");

        String categoryJson = mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                .content(mapper.writeValueAsString(testCategory))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Category> createdCategorylist = (List<Category>) mapper.readValue(categoryJson, List.class);
        Category createdCategory = mapper.convertValue(createdCategorylist.get(0), Category.class);

        createdCategory.setName("Sport");

        mockMvc.perform(MockMvcRequestBuilders.put("/categories")
                .content(mapper.writeValueAsString(createdCategory))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String tt = mockMvc.perform(MockMvcRequestBuilders.get("/categories/byId").param("categoryId", createdCategory.getCategoryId().toString()))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        List<Category> createdCategorylist1 = (List<Category>) mapper.readValue(tt, List.class);
        Category createdCategory1 = mapper.convertValue(createdCategorylist1.get(0), Category.class);

        assertTrue(createdCategory1.getName().equals("Sport"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/categories").content(tt).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/byId").param("categoryId", createdCategory1.getCategoryId().toString()))
                .andExpect(status().isNotFound());

    }
}
