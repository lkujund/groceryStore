package hr.grocery.store.grocerystore.repository;

import hr.grocery.store.grocerystore.model.Order;
import hr.grocery.store.grocerystore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJpaOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
}
