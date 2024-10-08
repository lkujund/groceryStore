package hr.grocery.store.grocerystore.repository;


import hr.grocery.store.grocerystore.model.GroceryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJpaGroceryCategoryRepository extends JpaRepository<GroceryCategory, Integer> {
    List<GroceryCategory> findByName(String name);
}
