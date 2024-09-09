package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.dto.GroceryDTO;
import hr.grocery.store.grocerystore.model.Grocery;
import hr.grocery.store.grocerystore.model.GrocerySearchForm;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface GroceryService {
    List<GroceryDTO> findAll();
    List<Grocery> findAllAdmin();
    Optional<GroceryDTO> findById(Integer id);
    Optional<Grocery> findByIdAdmin(Integer id);
    void save(GroceryDTO groceryDto) throws IOException;
    void edit(GroceryDTO groceryDto, int id) throws IOException;
    List<Grocery> filterByCriteria(GrocerySearchForm grocerySearchForm);
    List<GroceryDTO> findByName(String name);
    List<Grocery> findByNameAdmin(String name);
    void deleteById(Integer id);
}
