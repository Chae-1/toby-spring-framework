package tobyspring.hellospring;

import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.payment.Payment;
import tobyspring.hellospring.payment.PaymentService;

public class Client {
    public static void main(String[] args) {
        BeanFactory factory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = factory.getBean(PaymentService.class);

        Payment prepare = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("prepare = " + prepare);
    }
}
