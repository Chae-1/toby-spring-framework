package tobyspring.hellospring.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import tobyspring.hellospring.DataConfig;
import tobyspring.hellospring.data.JpaOrderRepository;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(JpaTransactionManager transactionManager) {
        return new JpaOrderRepository(transactionManager);
    }

    @Bean
    public OrderService orderService(JpaTransactionManager transactionManager) {
        return new OrderService(orderRepository(transactionManager));
    }
}
