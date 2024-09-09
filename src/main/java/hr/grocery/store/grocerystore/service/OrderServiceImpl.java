package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.model.Order;
import hr.grocery.store.grocerystore.model.User;
import hr.grocery.store.grocerystore.repository.SpringDataJpaOrderRepository;
import hr.grocery.store.grocerystore.repository.SpringDataJpaUserRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    private SpringDataJpaOrderRepository orderRepository;
    private SpringDataJpaUserRepository userRepository;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUser(String username) {
        User user = userRepository.findUserByUsername(username);
        return orderRepository.findByUser(user);
    }

    @Override
    public List<Order> getOrdersByDate(String date) {
        return List.of();
    }

}
