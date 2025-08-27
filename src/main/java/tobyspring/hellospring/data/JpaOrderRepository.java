package tobyspring.hellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.hellospring.order.Order;
import tobyspring.hellospring.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    private final JpaTransactionManager transactionManager;

    public JpaOrderRepository(JpaTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public Order save(Order order) {
        return new TransactionTemplate(transactionManager).execute((status) -> {
            em.persist(order);
            return order;
        });
    }
}
