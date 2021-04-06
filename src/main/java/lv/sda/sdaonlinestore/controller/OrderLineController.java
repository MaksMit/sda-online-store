package lv.sda.sdaonlinestore.controller;

import lv.sda.sdaonlinestore.entity.Order;
import lv.sda.sdaonlinestore.entity.OrderLine;
import lv.sda.sdaonlinestore.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/orderLines")
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    @GetMapping
    public ResponseEntity<List<OrderLine>> displayOrderLines() {
        List<OrderLine> allOrderLines = orderLineService.getAllOrderLines();
        return ResponseEntity.ok(allOrderLines);
    }

    @GetMapping("/byId")
    public ResponseEntity<List<OrderLine>> displayOrderLineById(@RequestParam Long orderLineId) {

        try {
            OrderLine orderLineById = orderLineService.findById(orderLineId);
            List<OrderLine> lst = new ArrayList<>();
            lst.add(orderLineById);
            return ResponseEntity.ok(lst);
        } catch (NoSuchElementException ne) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<List<OrderLine>> putOderLine(@RequestBody OrderLine orderLine) {
        orderLineService.update(orderLine,orderLine.getOrderLineId());
        List<OrderLine> lst = new ArrayList<>();
        lst.add(orderLine);
        return ResponseEntity.ok(lst);
    }

    @PostMapping
    public ResponseEntity<List<OrderLine>> addOrderLine(@RequestBody OrderLine orderLine) {
        orderLineService.save(orderLine);
        List<OrderLine> lst = new ArrayList<>();
        lst.add(orderLine);
        return ResponseEntity.ok(lst);
    }

    @DeleteMapping
    public String deleteOrderLine(@RequestBody List<OrderLine> orderLine) {
        orderLineService.delete(orderLine);
        return "OrderLine deleted";
    }

}

