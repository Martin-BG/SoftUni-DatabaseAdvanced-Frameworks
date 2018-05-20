package com.photographyworkshops.domain.entities.workshops;


import com.photographyworkshops.domain.entities.photographers.Photographer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "workshops")
public class Workshop implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @NotNull
    @Column(name = "location")
    private String location;

    @NotNull
    @Column(name = "price_per_participant")
    private BigDecimal pricePerParticipant;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Photographer trainer;

    @ManyToMany
    @JoinTable(name = "workshop_photographers",
            joinColumns = @JoinColumn(name = "workshop_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "photographer_id", referencedColumnName = "id"))
    private Set<Photographer> photographers;

    public Workshop() {
        this.setPhotographers(new HashSet<>());
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPricePerParticipant() {
        return this.pricePerParticipant;
    }

    public void setPricePerParticipant(BigDecimal pricePerParticipant) {
        this.pricePerParticipant = pricePerParticipant;
    }

    public Photographer getTrainer() {
        return this.trainer;
    }

    public void setTrainer(Photographer trainer) {
        this.trainer = trainer;
    }

    public Set<Photographer> getPhotographers() {
        return this.photographers;
    }

    public void setPhotographers(Set<Photographer> photographers) {
        this.photographers = photographers;
    }

    public void addPhotographer(Photographer photographer){
        this.getPhotographers().add(photographer);
    }
}
