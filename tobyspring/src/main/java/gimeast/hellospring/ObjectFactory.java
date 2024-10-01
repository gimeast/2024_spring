package gimeast.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {
    @Bean
    public PaymentService paymentService() {
        System.out.println("@Configuration의 paymentService() 실행은 한번만 된다.");
        return new PaymentService(exRateProvider());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("@Configuration의 orderService() 실행은 한번만 된다.");
        return new OrderService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
//        return new SimpleExRateProvider();
        return new WebApiExRateProvider();
    }
}

class OrderService {
    ExRateProvider exRateProvider;

    public OrderService(ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }
}