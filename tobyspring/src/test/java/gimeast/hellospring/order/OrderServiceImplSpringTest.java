package gimeast.hellospring.order;

import gimeast.hellospring.OrderConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceImplSpringTest {
    @Autowired
    OrderService orderService;

    @Autowired
    DataSource dataSoure;

    @Test
    @DisplayName("주문 생성")
    void createOrder() {
        Order order = orderService.createOrder("O10", BigDecimal.TEN);
        assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("주문 여러개 생성")
    void createOrders() {
        List<OrderReq> orderReqs = List.of(
                new OrderReq("O200", BigDecimal.ONE),
                new OrderReq("O201", BigDecimal.TWO)
        );

        List<Order> orders = orderService.createOrders(orderReqs);

        assertThat(orders).hasSize(2);
        orders.forEach(order -> {
            assertThat(order.getId()).isGreaterThan(0);
        });

    }

    @Test
    @DisplayName("주문 여러개 생성시 예외 발생")
    void createDuplicateOrders() {
        List<OrderReq> orderReqs = List.of(
                new OrderReq("O300", BigDecimal.ONE),
                new OrderReq("O300", BigDecimal.TWO)
        );

        assertThatThrownBy(() -> {
            orderService.createOrders(orderReqs);
        }).isInstanceOf(DataIntegrityViolationException.class);

        Long count = JdbcClient.create(dataSoure).sql("select count(*) from orders where no = 'O300'").query(Long.class).single();
        assertThat(count).isEqualTo(0);
    }
}
