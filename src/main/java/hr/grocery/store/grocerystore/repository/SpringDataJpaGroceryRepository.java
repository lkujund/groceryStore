package hr.grocery.store.grocerystore.repository;

import hr.grocery.store.grocerystore.model.Grocery;
import hr.grocery.store.grocerystore.model.GroceryCategory;
import hr.grocery.store.grocerystore.model.GrocerySearchForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface SpringDataJpaGroceryRepository extends JpaRepository<Grocery, Integer> {
    Grocery findByNameAndCategoryAndPriceAndDescription(
            String name, GroceryCategory category, BigDecimal price, String description);

    List<Grocery> findByNameContainingIgnoreCase(String name);
}
