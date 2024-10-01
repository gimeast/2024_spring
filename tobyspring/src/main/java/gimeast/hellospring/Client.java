package gimeast.hellospring;

import gimeast.hellospring.payment.Payment;
import gimeast.hellospring.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

//        OrderService orderService = beanFactory.getBean(OrderService.class);
//        System.out.println(orderService.exRateProvider == paymentService.exRateProvider); //true

        System.out.println("-----------------------------------");
        Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment1 = " + payment1);

        System.out.println("-----------------------------------");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1초 후");

        Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment2 = " + payment2);

        System.out.println("-----------------------------------");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("3초 후");

        Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment3 = " + payment3);
        System.out.println("-----------------------------------");
    }
}
