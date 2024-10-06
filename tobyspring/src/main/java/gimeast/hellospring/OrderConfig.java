package gimeast.hellospring;

import gimeast.hellospring.data.JdbcOrderRepository;
import gimeast.hellospring.order.OrderRepository;
import gimeast.hellospring.order.OrderService;
import gimeast.hellospring.order.OrderServiceImpl;
import gimeast.hellospring.order.OrderServiceTxProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
        return new OrderServiceTxProxy(new OrderServiceImpl(orderRepository), transactionManager);
    }
}
