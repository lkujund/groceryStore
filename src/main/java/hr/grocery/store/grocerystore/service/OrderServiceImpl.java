package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.model.Order;
import hr.grocery.store.grocerystore.model.OrderSearchForm;
import hr.grocery.store.grocerystore.model.User;
import hr.grocery.store.grocerystore.repository.SpringDataJpaOrderRepository;
import hr.grocery.store.grocerystore.repository.SpringDataJpaUserRepository;
import lombok.AllArgsConstructor;
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
        User user = userRepository.findByUsername(username).get();
        return orderRepository.findByUser(user);
    }

    @Override
    public List<Order> filterByCriteria(OrderSearchForm orderSearchForm) {
        List<Order> orderList = orderRepository.findAll();

        if (!orderSearchForm.getUsername().isEmpty())
        {
            orderList = orderList
                    .stream()
                    .filter(g -> g.getUser().getUsername().toLowerCase().contains(orderSearchForm.getUsername().toLowerCase()))
                    .toList();
        }

        if (orderSearchForm.getStartDate() != null)
        {
            orderList = orderList
                    .stream()
                    .filter(g -> g.getOrderTs().toInstant().isAfter(orderSearchForm.getStartDate().toInstant().minusSeconds(1)))
                    .toList();

        }

        if (orderSearchForm.getEndDate() != null)
        {
            orderList = orderList
                    .stream()
                    .filter(g -> g.getOrderTs().toInstant().isBefore(orderSearchForm.getEndDate().toInstant().plusSeconds(86399)))
                    .toList();

        }

        return orderList;
    }

}
