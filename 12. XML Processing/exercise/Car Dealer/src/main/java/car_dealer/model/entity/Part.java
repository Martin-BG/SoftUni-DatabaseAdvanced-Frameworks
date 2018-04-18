package car_dealer.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "parts")
public class Part implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne
    private Supplier supplier;

    @ManyToMany(mappedBy = "parts")
    private Set<Car> cars;

    public Part() {
        this.cars = new HashSet<>();
    }
}
