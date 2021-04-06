package lv.sda.sdaonlinestore.crudTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.sda.sdaonlinestore.entity.OrderLine;
import org.junit.jupiter.api.Test;
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

public class OrderLineControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;

    @Test
    public void testOrderFullCrud() throws Exception {
        //create new orderLine
        OrderLine testOrderLine = new OrderLine();
        testOrderLine.setQuantity(1);
        testOrderLine.setProductPrice(BigDecimal.valueOf(2.00)); //from products table, price on buy
        testOrderLine.setProduct("Product description");

        String orderJson = mockMvc.perform(MockMvcRequestBuilders.post("/orderLines")
                .content(mapper.writeValueAsString(testOrderLine))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<OrderLine> createdOrderLineList = (List<OrderLine>) mapper.readValue(orderJson, List.class);
        OrderLine createdOrderLine = mapper.convertValue(createdOrderLineList.get(0), OrderLine.class);

        // Update OrderLine
        createdOrderLine.setProduct("New name1");

        mockMvc.perform(MockMvcRequestBuilders.put("/orderLines")
                .content(mapper.writeValueAsString(createdOrderLine))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Read check OrderLine updated
        String tt = mockMvc.perform(MockMvcRequestBuilders.get("/orderLines/byId").param("orderLineId", createdOrderLine.getOrderLineId().toString()))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        List<OrderLine> createdOrderLineList1 = (List<OrderLine>) mapper.readValue(tt, List.class);
        OrderLine createdOrderLine1 = mapper.convertValue(createdOrderLineList1.get(0), OrderLine.class);

        assertTrue(createdOrderLine1.getProduct().equals("New name1"));

        // Delete OrderLine
        mockMvc.perform(MockMvcRequestBuilders.delete("/orderLines").content(tt).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Check OrderLine quantity as before
        mockMvc.perform(MockMvcRequestBuilders.get("/orderLines/byId").param("orderLineId", createdOrderLine1.getOrderLineId().toString()))
                .andExpect(status().isNotFound());
    }


}

