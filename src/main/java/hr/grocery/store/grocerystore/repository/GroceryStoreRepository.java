package hr.grocery.store.grocerystore.repository;

import hr.grocery.store.grocerystore.model.Grocery;
import hr.grocery.store.grocerystore.model.GrocerySearchForm;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GroceryStoreRepository {

    List<Grocery> findAll();
    Optional<Grocery> findById(Integer id);
    void save(Grocery grocery);

    List<Grocery> filterByCriteria(GrocerySearchForm grocerySearchForm);
}
