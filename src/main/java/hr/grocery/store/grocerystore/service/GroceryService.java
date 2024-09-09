package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.dto.GroceryDTO;
import hr.grocery.store.grocerystore.model.Grocery;
import hr.grocery.store.grocerystore.model.GrocerySearchForm;

import java.util.List;
import java.util.Optional;

public interface GroceryService {
    List<GroceryDTO> findAll();
    List<Grocery> findAllAdmin();
    Optional<GroceryDTO> findById(Integer id);
    Optional<Grocery> findByIdAdmin(Integer id);
    void save(GroceryDTO groceryDto);
    void edit(GroceryDTO groceryDto, int id);
    List<GroceryDTO> filterByCriteria(GrocerySearchForm grocerySearchForm);
    List<GroceryDTO> findByName(String name);
    void deleteById(Integer id);
}
