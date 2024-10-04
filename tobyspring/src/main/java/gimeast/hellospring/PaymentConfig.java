package gimeast.hellospring;

import gimeast.hellospring.api.ApiExecutor;
import gimeast.hellospring.api.ApiTemplate;
import gimeast.hellospring.api.ErApiExRateExtractor;
import gimeast.hellospring.api.ExRateExtractor;
import gimeast.hellospring.api.HttpClientApiExecutor;
import gimeast.hellospring.api.SimpleApiExecutor;
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
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate(new HttpClientApiExecutor(), new ErApiExRateExtractor());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider(apiTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}