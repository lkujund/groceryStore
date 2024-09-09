package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.dto.GroceryDTO;
import hr.grocery.store.grocerystore.model.Grocery;
import hr.grocery.store.grocerystore.model.GrocerySearchForm;

import java.util.List;
import java.util.Optional;

public interface GroceryService {
    List<GroceryDTO> findAll();
    Optional<GroceryDTO> findById(Integer id);
    void save(GroceryDTO groceryDto);
    List<GroceryDTO> filterByCriteria(GrocerySearchForm grocerySearchForm);
    List<GroceryDTO> findByName(String name);
}
