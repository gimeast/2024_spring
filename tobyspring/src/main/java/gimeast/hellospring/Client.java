package gimeast.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);
        OrderService orderService = beanFactory.getBean(OrderService.class);

        System.out.println("orderService.exRateProvider = " + orderService.exRateProvider);
        System.out.println("paymentService.exRateProvider = " + paymentService.exRateProvider);

        System.out.println(orderService.exRateProvider == paymentService.exRateProvider); //true

        Payment prepare = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("prepare = " + prepare);
    }
}
