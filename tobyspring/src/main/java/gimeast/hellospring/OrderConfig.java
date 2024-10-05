package gimeast.hellospring;

import gimeast.hellospring.data.JpaOrderRepository;
import gimeast.hellospring.data.JpaOrderRepositoryTemplate;
import gimeast.hellospring.order.OrderRepository;
import gimeast.hellospring.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public JpaOrderRepositoryTemplate jpaOrderRepositoryTemplate() {
        return new JpaOrderRepositoryTemplate();
    }

    @Bean
    public OrderRepository orderRepository() {
        return new JpaOrderRepository(jpaOrderRepositoryTemplate());
    }

    @Bean
    public OrderService orderService(JpaTransactionManager transactionManager) {
        return new OrderService(orderRepository(), transactionManager);
    }
}
