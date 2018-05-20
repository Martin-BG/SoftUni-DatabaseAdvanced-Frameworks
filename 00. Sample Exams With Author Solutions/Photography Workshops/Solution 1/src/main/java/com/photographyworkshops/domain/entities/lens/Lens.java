package com.photographyworkshops.domain.entities.lens;

import com.google.gson.annotations.SerializedName;
import com.photographyworkshops.domain.entities.photographers.Photographer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lenses")
public class Lens implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "make")
    private String make;

    @Column(name = "focal_length")
    private int focalLength;

    @Column(name = "max_aperture")
    private float maxAperture;

    @Column(name = "compatible_make")
    private String compatibleMake;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    public Lens() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getFocalLength() {
        return this.focalLength;
    }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }

    public float getMaxAperture() {
        return this.maxAperture;
    }

    public void setMaxAperture(float maxAperture) {
        this.maxAperture = maxAperture;
    }

    public String getCompatibleMake() {
        return this.compatibleMake;
    }

    public void setCompatibleMake(String compatibleMake) {
        this.compatibleMake = compatibleMake;
    }

    public Photographer getPhotographer() {
        return this.photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }
}
