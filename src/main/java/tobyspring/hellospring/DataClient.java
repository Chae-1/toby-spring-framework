package tobyspring.hellospring;

import java.math.BigDecimal;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.order.Order;
import tobyspring.hellospring.order.OrderRepository;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory factory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = factory.getBean(OrderRepository.class);

        Order order = new Order("100", BigDecimal.TEN);
        repository.save(order);

        try {
            Order order2 = new Order("100", BigDecimal.TEN);
            repository.save(order2);
        } catch (ConstraintViolationException e) {
            System.out.println("주문번호 충돌을 복구하는 작업");
        }
    }
}
