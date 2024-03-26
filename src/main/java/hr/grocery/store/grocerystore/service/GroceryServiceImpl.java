package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.dto.GroceryDTO;
import hr.grocery.store.grocerystore.model.Grocery;
import hr.grocery.store.grocerystore.model.GroceryCategory;
import hr.grocery.store.grocerystore.model.GrocerySearchForm;
import hr.grocery.store.grocerystore.model.MeasuringUnit;
import hr.grocery.store.grocerystore.repository.GroceryStoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroceryServiceImpl implements GroceryService{

    private GroceryStoreRepository groceryStoreRepository;
    @Override
    public List<GroceryDTO> findAll() {
        return groceryStoreRepository
                .findAll()
                .stream()
                .map(this::convertGroceryToGroceryDTO)
                .toList();
    }

    @Override
    public Optional<GroceryDTO> findById(Integer id) {
        return groceryStoreRepository.findById(id)
                .map(this::convertGroceryToGroceryDTO);
    }

    @Override
    public void save(GroceryDTO groceryDto) {
        groceryStoreRepository.save(convertGroceryDTOToGrocery(groceryDto));
    }

    @Override
    public List<GroceryDTO> filterByCriteria(GrocerySearchForm grocerySearchForm) {
        return groceryStoreRepository.filterByCriteria(grocerySearchForm)
                .stream()
                .map(this::convertGroceryToGroceryDTO)
                .toList();
    }

    private GroceryDTO convertGroceryToGroceryDTO(Grocery grocery){
        return new GroceryDTO(
                grocery.getName(),
                grocery.getCategory().name(),
                grocery.getMeasuringUnit().name(),
                grocery.getMeasure(),
                grocery.getPrice(),
                grocery.getDescription(),
                grocery.getImage()
        );
    }

    private Grocery convertGroceryDTOToGrocery(GroceryDTO groceryDTO)
    {
        return new Grocery(
                groceryDTO.getName(),
                GroceryCategory.valueOf(groceryDTO.getCategoryString()),
                MeasuringUnit.valueOf(groceryDTO.getMeasuringUnitString()),
                groceryDTO.getMeasure(),
                groceryDTO.getPrice(),
                groceryDTO.getDescription(),
                groceryDTO.getImage()
        );
    }
}
