package gimeast.hellospring.data;

import gimeast.hellospring.order.Order;
import gimeast.hellospring.order.OrderRepository;


public class JpaOrderRepository implements OrderRepository {
    private final JpaOrderRepositoryTemplate jpaOrderRepositoryTemplate;

    public JpaOrderRepository(JpaOrderRepositoryTemplate jpaOrderRepositoryTemplate) {
        this.jpaOrderRepositoryTemplate = jpaOrderRepositoryTemplate;
    }

    @Override
    public void save(Order order) {
        jpaOrderRepositoryTemplate.save(order);
    }
}
