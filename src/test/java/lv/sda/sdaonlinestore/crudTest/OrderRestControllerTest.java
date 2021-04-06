package lv.sda.sdaonlinestore.crudTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.sda.sdaonlinestore.entity.Order;
import lv.sda.sdaonlinestore.entity.Status;
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
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class OrderRestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;

    @Test
    public void testOrderFullCrud() throws Exception {

        // Create new Order
        Order testOrder = new Order();
        testOrder.setUserName("Nikolas9");
        testOrder.setTotalCost(BigDecimal.valueOf(24));
        testOrder.setDeliveryAddress("Street-24-1");
        testOrder.setUserAddress("Street-24-1");
        testOrder.setOrderDate(new Date());
        testOrder.setStatus(Status.submitted);

        String orderJson = mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                .content(mapper.writeValueAsString(testOrder))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Order> createdOrderlist = (List<Order>) mapper.readValue(orderJson, List.class); // -> List<Object> ((List<Order>))
        Order createdOrder = mapper.convertValue(createdOrderlist.get(0), Order.class);

        // Update Order
        createdOrder.setUserName("New name1");

        mockMvc.perform(MockMvcRequestBuilders.put("/orders")
                .content(mapper.writeValueAsString(createdOrder))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Read check Order updated
       String tt = mockMvc.perform(MockMvcRequestBuilders.get("/orders/byId").param("orderId", createdOrder.getOrderId().toString()))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        List<Order> createdOrderlist1 = (List<Order>) mapper.readValue(tt, List.class); // -> List<Object> ((List<Order>))
        Order createdOrder1 = mapper.convertValue(createdOrderlist1.get(0), Order.class);

        assertTrue(createdOrder1.getUserName().equals("New name1"));

        // Delete Order
        mockMvc.perform(MockMvcRequestBuilders.delete("/orders").content(tt).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Check Order size same as before
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/byId").param("orderId", createdOrder1.getOrderId().toString()))
                .andExpect(status().isNotFound());
    }
}

