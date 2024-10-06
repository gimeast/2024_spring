package gimeast.hellospring.order;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceTxProxy implements OrderService{
    private final OrderService target;
    private final PlatformTransactionManager txManager;

    public OrderServiceTxProxy(OrderService target, PlatformTransactionManager txManager) {
        this.target = target;
        this.txManager = txManager;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        return new TransactionTemplate(txManager)
                .execute(status -> target.createOrder(no, total));
    }

    @Override
    public List<Order> createOrders(List<OrderReq> reqs) {
        return new TransactionTemplate(txManager)
                .execute(status -> target.createOrders(reqs));
    }
}
