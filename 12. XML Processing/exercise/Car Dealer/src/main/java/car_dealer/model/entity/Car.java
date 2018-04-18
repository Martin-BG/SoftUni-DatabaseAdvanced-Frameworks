package car_dealer.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cars")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;

    private String model;

    private Long travelledDistance;

    @ManyToMany()
    @JoinTable(name = "parts_cars",
            joinColumns = {@JoinColumn(name = "car_id")},
            inverseJoinColumns = {@JoinColumn(name = "part_id")})
    private Set<Part> parts;

    public Car() {
        this.parts = new HashSet<>();
    }
}
