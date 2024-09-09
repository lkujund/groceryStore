package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.GroceryStoreApplication;
import hr.grocery.store.grocerystore.dto.GroceryDTO;
import hr.grocery.store.grocerystore.model.Grocery;
import hr.grocery.store.grocerystore.model.GroceryCategory;
import hr.grocery.store.grocerystore.model.GrocerySearchForm;
import hr.grocery.store.grocerystore.model.MeasuringUnit;
import hr.grocery.store.grocerystore.repository.GroceryStoreRepository;
import hr.grocery.store.grocerystore.repository.SpringDataJpaGroceryCategoryRepository;
import hr.grocery.store.grocerystore.repository.SpringDataJpaGroceryRepository;
import hr.grocery.store.grocerystore.repository.SpringDataJpaMeasuringUnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroceryServiceImpl implements GroceryService{

    private SpringDataJpaGroceryRepository groceryStoreRepository;
    private SpringDataJpaGroceryCategoryRepository groceryCategoryRepository;
    private SpringDataJpaMeasuringUnitRepository measuringUnitRepository;

    @Override
    public List<GroceryDTO> findAll() {
        return groceryStoreRepository
                .findAll()
                .stream()
                .map(this::convertGroceryToGroceryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GroceryDTO> findById(Integer id) {
        return groceryStoreRepository.findById(id)
                .stream()
                .map(this::convertGroceryToGroceryDTO)
                .findFirst();
    }

    @Override
    public void save(GroceryDTO groceryDto) {
        groceryStoreRepository.save(convertGroceryDTOToGrocery(groceryDto));
    }

    @Override
    public List<GroceryDTO> filterByCriteria(GrocerySearchForm grocerySearchForm) {
        List<Grocery> groceryList = groceryStoreRepository.findAll();

        if (!grocerySearchForm.getName().isEmpty())
        {
            groceryList = groceryList
                    .stream()
                    .filter(g -> g.getName().toLowerCase().contains(grocerySearchForm.getName().toLowerCase()))
                    .toList();
        }

        if (!grocerySearchForm.getCategory().isEmpty())
        {
            groceryList = groceryList
                    .stream()
                    .filter(g -> g.getCategory().getName().equals(grocerySearchForm.getCategory()))
                    .toList();

        }

        if (Optional.ofNullable(grocerySearchForm.getLowerPrice()).isPresent())
        {
            groceryList = groceryList
                    .stream()
                    .filter(g -> g.getPrice().compareTo(grocerySearchForm.getLowerPrice()) >= 0)
                    .toList();

        }
        if (Optional.ofNullable(grocerySearchForm.getUpperPrice()).isPresent())
        {
            groceryList = groceryList
                    .stream()
                    .filter(g -> g.getPrice().compareTo(grocerySearchForm.getUpperPrice()) <= 0)
                    .toList();
        }

        if (!grocerySearchForm.getDescription().isEmpty())
        {
            groceryList = groceryList
                    .stream()
                    .filter(g -> g.getDescription().toLowerCase().contains(grocerySearchForm.getDescription().toLowerCase()))
                    .toList();
        }

        return groceryList
                .stream()
                .map(this::convertGroceryToGroceryDTO)
                .toList();
    }

    @Override
    public List<GroceryDTO> findByName(String name) {
        if (name.equals("*")){
            return groceryStoreRepository.findAll()
                    .stream().map(this::convertGroceryToGroceryDTO)
                    .toList();
        }else {
            return groceryStoreRepository.findByNameContainingIgnoreCase(name)
                    .stream().map(this::convertGroceryToGroceryDTO)
                    .toList();
        }
    }

    private GroceryDTO convertGroceryToGroceryDTO(Grocery grocery){
        return new GroceryDTO(
                grocery.getName(),
                grocery.getCategory().getName(),
                grocery.getMeasuringUnit().getName(),
                grocery.getMeasure(),
                grocery.getPrice(),
                grocery.getDescription(),
                grocery.getImage()
        );
    }

    private Grocery convertGroceryDTOToGrocery(GroceryDTO groceryDTO)
    {
        List<GroceryCategory> groceryCategoryList =
                groceryCategoryRepository.findByName(groceryDTO.getCategoryString());

        GroceryCategory groceryCategory = groceryCategoryList.getFirst();

        List<MeasuringUnit> measuringUnitList =
                measuringUnitRepository.findByName(groceryDTO.getCategoryString());

        MeasuringUnit measuringUnit = measuringUnitList.getFirst();

        return new Grocery(
                groceryDTO.getName(),
                groceryCategory,
                measuringUnit,
                groceryDTO.getMeasure(),
                groceryDTO.getPrice(),
                groceryDTO.getDescription(),
                groceryDTO.getImage()
        );
    }
}
