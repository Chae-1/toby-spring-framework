package tobyspring.hellospring;

import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.hellospring.order.Order;
import tobyspring.hellospring.order.OrderRepository;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory factory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = factory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = factory.getBean(JpaTransactionManager.class);

        try {
            new TransactionTemplate(transactionManager).execute((transactionStatus) -> {
                Order order = new Order("100", BigDecimal.TEN);
                repository.save(order);

                Order order2 = new Order("100", BigDecimal.TEN);
                repository.save(order2);

                return null;
            });
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
        // commit

    }
}
