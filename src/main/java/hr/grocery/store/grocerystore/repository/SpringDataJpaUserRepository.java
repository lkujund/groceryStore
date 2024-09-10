package hr.grocery.store.grocerystore.repository;

import hr.grocery.store.grocerystore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataJpaUserRepository extends JpaRepository<User, Long> {
    User findUserById(Integer id);
    Optional<User> findByUsername(String username);
}