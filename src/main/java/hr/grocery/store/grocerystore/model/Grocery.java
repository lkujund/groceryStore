package hr.grocery.store.grocerystore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Base64;

@Entity
@Table(name = "GROCERY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grocery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private GroceryCategory category;

    @ManyToOne
    @JoinColumn(name = "MEASURING_UNIT_ID")
    private MeasuringUnit measuringUnit;

    @Column(name = "MEASURE")
    private BigDecimal measure;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "DESCRIPTION")
    private String description;

    @Lob
    @Column(name = "IMAGE")
    private byte[] image;

    @Column(columnDefinition = "TEXT")
    private String b64Image;

    public Grocery(String name, GroceryCategory category, MeasuringUnit measuringUnit, BigDecimal measure, BigDecimal price, String description, byte[] image) {
        this.name = name;
        this.category = category;
        this.measuringUnit = measuringUnit;
        this.measure = measure;
        this.price = price;
        this.description = description;
        this.image = image;
        this.b64Image = Base64.getEncoder().encodeToString(this.image);
    }
}
