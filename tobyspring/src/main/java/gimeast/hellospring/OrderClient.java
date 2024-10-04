package gimeast.hellospring;

import gimeast.hellospring.order.Order;
import gimeast.hellospring.order.OrderService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class OrderClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService service = beanFactory.getBean(OrderService.class);

        Order order = service.createOrder("100", BigDecimal.TEN);
        System.out.println("order = " + order);

    }
}
