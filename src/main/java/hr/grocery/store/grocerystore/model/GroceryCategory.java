package hr.grocery.store.grocerystore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "GROCERY_CATEGORY")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Getter
@Setter
public class GroceryCategory {

    public GroceryCategory(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
}
