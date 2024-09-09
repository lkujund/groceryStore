package hr.grocery.store.grocerystore.repository;

import hr.grocery.store.grocerystore.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroceryStoreRepositoryMock implements GroceryStoreRepository {
    private static List<Grocery> groceryList;

    static {
        groceryList = new ArrayList<>();

        Grocery grocery = new Grocery();
        grocery.setId(1);
        grocery.setName("Banana");
        grocery.setMeasuringUnit(new MeasuringUnit(1, MeasuringUnitEnum.KILOGRAM.name()));
        grocery.setMeasure(new BigDecimal(1));
        grocery.setCategory(new GroceryCategory(1, GroceryCategoryEnum.FRUITS_AND_VEGETABLES.name()));
        grocery.setDescription("Banana");
        grocery.setPrice(new BigDecimal("1.3"));

        groceryList.add(grocery);
    }

    @Override
    public List<Grocery> findAll() {
        return groceryList;
    }

    @Override
    public Optional<Grocery> findById(Integer id) {
        return groceryList
                .stream()
                .filter(g -> g.getId().equals(id))
                .findFirst();
    }

    @Override
    public void save(Grocery grocery) {
        grocery.setId(generateNewId());
        groceryList.add(grocery);
    }

    @Override
    public List<Grocery> filterByCriteria(GrocerySearchForm grocerySearchForm) {
        List<Grocery> groceryList = findAll();

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

        if (!Optional.ofNullable(grocerySearchForm.getLowerPrice()).isPresent())
        {
            groceryList = groceryList
                    .stream()
                    .filter(g -> g.getPrice().compareTo(grocerySearchForm.getLowerPrice()) >= 0)
                    .toList();

        }
        if (!Optional.ofNullable(grocerySearchForm.getUpperPrice()).isPresent())
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

        return groceryList;
    }

    public Integer generateNewId(){
        Optional<Integer> maxId =
                groceryList
                    .stream()
                    .max((g1, g2) -> g1.getId().compareTo(g2.getId()))
                    .map(Grocery::getId);

        return maxId.map(integer -> integer + 1).orElse(1);
    }
}
