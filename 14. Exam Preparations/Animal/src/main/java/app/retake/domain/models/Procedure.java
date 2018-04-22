package app.retake.domain.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "procedures")
public class Procedure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "procedures")
    private Set<AnimalAid> services;

    @ManyToOne
    private Animal animal;

    @ManyToOne
    private Vet vet;

    @Column(name = "date_performed")
    private Date date;

    public Procedure() {
        this.services = new HashSet<>();
    }

    public BigDecimal getCost() {
        return this.services
                .stream()
                .map(AnimalAid::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @PostPersist
    private void updateAids() {
        this.getServices().forEach(animalAid -> animalAid.getProcedures().add(this));
    }
}
