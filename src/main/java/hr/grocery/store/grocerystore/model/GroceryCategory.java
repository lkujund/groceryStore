package hr.grocery.store.grocerystore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GROCERY_CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class GroceryCategory {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "ID")
private Integer id;
    @Column(name = "NAME")
    private String name;
}
