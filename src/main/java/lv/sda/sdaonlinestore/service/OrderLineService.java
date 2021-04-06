package lv.sda.sdaonlinestore.service;

import lv.sda.sdaonlinestore.entity.OrderLine;
import lv.sda.sdaonlinestore.repo.OrderLineRepository;
import lv.sda.sdaonlinestore.repo.StoreUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderLineService {
    @Autowired
    OrderLineRepository orderLineRepository;

    public OrderLine findById(Long id) {
        return orderLineRepository.findById(id).get();
    }

    public List<OrderLine> getAllOrderLines() {
        return orderLineRepository.findAll();
    }

    public OrderLine saveOrUpdate(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    public OrderLine update(OrderLine orderLine, Long orderLineId) {
        return orderLineRepository.save(orderLine);
    }

    public OrderLine save(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    public void delete(List<OrderLine> orderLine) {
        orderLineRepository.deleteAll(orderLine);
    }

    public void deleteById(Long orderLineId) {
        orderLineRepository.deleteById(orderLineId);
    }
}
