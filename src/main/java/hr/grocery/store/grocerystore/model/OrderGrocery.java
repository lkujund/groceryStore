package hr.grocery.store.grocerystore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ORDER_GROCERY")
@AllArgsConstructor
@NoArgsConstructor
public class OrderGrocery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COUNT")
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROCERY_ID", referencedColumnName = "id")
    private Grocery grocery;

}
