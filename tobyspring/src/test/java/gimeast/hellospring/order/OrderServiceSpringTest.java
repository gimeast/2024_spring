package gimeast.hellospring.order;

import gimeast.hellospring.OrderConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {
    @Autowired
    OrderService orderService;

    @Test
    @DisplayName("주문 생성")
    void createOrder() {
        Order order = orderService.createOrder("0", BigDecimal.TEN);
        assertThat(Integer.parseInt(order.getNo())).isGreaterThan(0);
    }

}
