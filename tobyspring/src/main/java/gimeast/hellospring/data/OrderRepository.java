package gimeast.hellospring.data;

import gimeast.hellospring.order.Order;


public class OrderRepository {
    private final OrderRepositoryTemplate orderRepositoryTemplate;

    public OrderRepository(OrderRepositoryTemplate orderRepositoryTemplate) {
        this.orderRepositoryTemplate = orderRepositoryTemplate;
    }

    public void save(Order order) {
        orderRepositoryTemplate.save(order);
    }
}
