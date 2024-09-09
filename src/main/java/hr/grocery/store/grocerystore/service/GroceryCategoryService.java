package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.dto.GroceryCategoryDTO;
import hr.grocery.store.grocerystore.dto.GroceryDTO;
import hr.grocery.store.grocerystore.model.GroceryCategory;
import hr.grocery.store.grocerystore.model.GrocerySearchForm;

import java.util.List;
import java.util.Optional;

public interface GroceryCategoryService {
    List<GroceryCategoryDTO> findAll();
    List<GroceryCategory> findAllAdmin();
    Optional<GroceryCategory> findByIdAdmin(Integer id);
    void save(GroceryCategoryDTO groceryCategoryDto);
    void edit(GroceryCategoryDTO groceryCategoryDto, int id);
    void deleteById(Integer id);
}
