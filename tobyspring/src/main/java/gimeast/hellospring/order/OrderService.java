package gimeast.hellospring.order;

import gimeast.hellospring.data.OrderRepository;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final JpaTransactionManager transactionManager;

    public OrderService(OrderRepository repository, JpaTransactionManager transactionManager) {
        this.repository = repository;
        this.transactionManager = transactionManager;
    }

    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);

        return new TransactionTemplate(transactionManager).execute(status -> {
            this.repository.save(order);
            return order;
        });
    }
}
