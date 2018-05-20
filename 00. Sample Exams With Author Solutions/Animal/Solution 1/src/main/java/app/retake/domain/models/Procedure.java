package app.retake.domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "procedures")
public class Procedure implements Serializable {

    private Integer id;
    private Animal animal;
    private Vet vet;
    private Date datePerformed;
    private Set<AnimalAid> services;

    public Procedure() {
        this.services = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Transient
    public BigDecimal getCost() {
        BigDecimal cost = BigDecimal.ZERO;
        for (AnimalAid service : this.services) {
            cost = cost.add(service.getPrice());
        }
        return cost;
    }

    @ManyToOne
    public Vet getVet() {
        return this.vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Date getDatePerformed() {
        return this.datePerformed;
    }

    public void setDatePerformed(Date datePerformed) {
        this.datePerformed = datePerformed;
    }

    @ManyToMany
    public Set<AnimalAid> getServices() {
        return this.services;
    }

    public void setServices(Set<AnimalAid> services) {
        this.services = services;
    }
}
