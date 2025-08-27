package tobyspring.hellospring;

import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.order.Order;
import tobyspring.hellospring.order.OrderConfig;
import tobyspring.hellospring.order.OrderService;

public class OrderClient {
    public static void main(String[] args) {
        BeanFactory factory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService orderService = factory.getBean(OrderService.class);

        // commit
        Order order = orderService.createOrder("1000", BigDecimal.TEN);
        System.out.println("order = " + order);
    }
}
