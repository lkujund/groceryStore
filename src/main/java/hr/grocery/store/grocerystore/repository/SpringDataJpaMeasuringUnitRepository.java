package hr.grocery.store.grocerystore.repository;


import hr.grocery.store.grocerystore.model.GroceryCategory;
import hr.grocery.store.grocerystore.model.MeasuringUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJpaMeasuringUnitRepository extends JpaRepository<MeasuringUnit, Integer> {
    List<MeasuringUnit> findByName(String name);
}
