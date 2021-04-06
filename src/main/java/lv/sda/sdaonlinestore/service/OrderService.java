package lv.sda.sdaonlinestore.service;

import lv.sda.sdaonlinestore.entity.Order;
import lv.sda.sdaonlinestore.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    @Transactional
    public Order saveOrUpdate(Order order)
    {
        return orderRepository.save(order);
    }
    @Transactional
    public Order update(Order order, Long storeUserId)
    {
        return orderRepository.save(order);
    }
    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }
    @Transactional
    public void delete(Order order) {
        orderRepository.delete(order);
    }
    @Transactional
    public void deleteById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
