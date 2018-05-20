package com.photographyworkshops.domain.entities.accessories;

import com.photographyworkshops.domain.entities.photographers.Photographer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "accessories")
public class Accessory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    public Accessory() {
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

    public Photographer getPhotographer() {
        return this.photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }
}
