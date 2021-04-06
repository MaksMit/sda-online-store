package lv.sda.sdaonlinestore.controller;

import lv.sda.sdaonlinestore.entity.Order;
import lv.sda.sdaonlinestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/orders")
public class OrderController {
    //localhost:8080/users -> POST StoreUser //add user
    //localhost:8080/users -> GET  //List of all users
    //localhost:8080/users PUT/POST StoreUser change
    //localhost:8080/users DELETE

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> displayOrders() {
        List<Order> allStoreUsers = orderService.getAllOrders();
        return ResponseEntity.ok(allStoreUsers);
    }

    @GetMapping("/byId") //    /users/byId
    public ResponseEntity<List<Order>> displayOrderById(@RequestParam Long orderId) {

        try {
            Order orderById = orderService.findById(orderId); //null if not found
            List<Order> lst = new ArrayList<>();
            lst.add(orderById);
            return ResponseEntity.ok(lst);
        } catch (NoSuchElementException ne) {
            return ResponseEntity.notFound().build();
        }


    }

    @PostMapping //    /users/addOrUpdate
    public List<Order> addOrder(@RequestBody Order order) {
        orderService.save(order);
        List<Order> lst = new ArrayList<>();
        lst.add(order);
        return lst;
    }

    @PutMapping //    /users/addOrUpdate
    public List<Order> updateOrderUpdate(@RequestBody Order order) {
        orderService.update(order, order.getOrderId());
        List<Order> lst = new ArrayList<>();
        lst.add(order);
        return lst;
    }

    @DeleteMapping //    /users/delete
    public String deleteOrder(@RequestBody List<Order> order) {
        order.forEach(o -> {
            orderService.delete(o);
        });
        return "User deleted";
    }

}

