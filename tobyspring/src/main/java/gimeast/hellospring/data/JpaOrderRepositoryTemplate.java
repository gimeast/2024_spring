package gimeast.hellospring.data;

import gimeast.hellospring.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaOrderRepositoryTemplate {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Order order) {
        this.entityManager.persist(order);
    }
}
