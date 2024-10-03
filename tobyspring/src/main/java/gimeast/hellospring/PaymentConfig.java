package gimeast.hellospring;

import gimeast.hellospring.payment.ExRateProvider;
import gimeast.hellospring.exrate.WebApiExRateProvider;
import gimeast.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        System.out.println("@Configuration의 paymentService() 실행은 한번만 된다.");
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider() {
//        return new SimpleExRateProvider();
        return new WebApiExRateProvider();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}