package org.softuni.mostwanted.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor()
@Entity
@Table(name = "racers")
public class Racer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer age;

    private BigDecimal bounty;

    @ManyToOne
    private Town homeTown;

    @OneToMany(mappedBy = "racer")
    private Set<Car> cars = new HashSet<>();

}
