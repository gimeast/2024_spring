package gimeast.hellospring;

import gimeast.hellospring.data.OrderRepository;
import gimeast.hellospring.data.OrderRepositoryTemplate;
import gimeast.hellospring.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public OrderRepositoryTemplate orderRepositoryTemplate() {
        return new OrderRepositoryTemplate();
    }

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepository(orderRepositoryTemplate());
    }

    @Bean
    public OrderService orderService(JpaTransactionManager transactionManager) {
        return new OrderService(orderRepository(), transactionManager);
    }
}
