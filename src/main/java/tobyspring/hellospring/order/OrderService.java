package tobyspring.hellospring.order;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository= orderRepository;
    }

    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);
        orderRepository.save(order);
        return order;
    }
}
