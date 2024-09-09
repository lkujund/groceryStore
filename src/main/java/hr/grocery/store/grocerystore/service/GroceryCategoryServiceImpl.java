package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.dto.GroceryCategoryDTO;
import hr.grocery.store.grocerystore.dto.GroceryDTO;
import hr.grocery.store.grocerystore.model.GroceryCategory;
import hr.grocery.store.grocerystore.model.GrocerySearchForm;
import hr.grocery.store.grocerystore.repository.SpringDataJpaGroceryCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroceryCategoryServiceImpl implements GroceryCategoryService {

    private SpringDataJpaGroceryCategoryRepository groceryCategoryRepository;

    @Override
    public List<GroceryCategoryDTO> findAll() {
        return groceryCategoryRepository.findAll()
                .stream().map(g -> new GroceryCategoryDTO(g.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<GroceryCategory> findAllAdmin() {
        return groceryCategoryRepository.findAll();
    }

    @Override
    public Optional<GroceryCategory> findByIdAdmin(Integer id) {
        return groceryCategoryRepository.findById(id);
    }

    @Override
    public void save(GroceryCategoryDTO groceryCategoryDto) {
        groceryCategoryRepository.save(new GroceryCategory(groceryCategoryDto.getName()));
    }

    @Override
    public void edit(GroceryCategoryDTO groceryCategoryDto, int id) {
        GroceryCategory groceryCategory = groceryCategoryRepository.findById(id).get();
        groceryCategory.setName(groceryCategoryDto.getName());
        groceryCategoryRepository.save(groceryCategory);
    }

    @Override
    public void deleteById(Integer id) {
        groceryCategoryRepository.deleteById(id);
    }
}
