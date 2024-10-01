package gimeast.hellospring;

import gimeast.hellospring.exrate.CachedExRateProvider;
import gimeast.hellospring.payment.ExRateProvider;
import gimeast.hellospring.exrate.WebApiExRateProvider;
import gimeast.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {
    @Bean
    public PaymentService paymentService() {
        System.out.println("@Configuration의 paymentService() 실행은 한번만 된다.");
        return new PaymentService(cachedExRateProvider());
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

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }
}

class OrderService {
    ExRateProvider exRateProvider;

    public OrderService(ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }
}