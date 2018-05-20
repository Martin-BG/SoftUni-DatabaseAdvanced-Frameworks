package com.photographyworkshops.domain.entities.photographers;

import com.photographyworkshops.domain.entities.accessories.Accessory;
import com.photographyworkshops.domain.entities.cameras.BasicCamera;
import com.photographyworkshops.domain.entities.lens.Lens;
import com.photographyworkshops.validation.annotations.Phone;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Photographer implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 50)
    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Phone
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "primary_camera")
    private BasicCamera primaryCamera;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "secondary_camera")
    private BasicCamera secondaryCamera;

    @OneToMany(mappedBy = "photographer")
    private Set<Lens> lenses;

    @OneToMany(mappedBy = "photographer", cascade = CascadeType.ALL)
    private Set<Accessory> accessories;

    public Photographer() {
        this.setLenses(new HashSet<>());
        this.setAccessories(new HashSet<>());
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BasicCamera getPrimaryCamera() {
        return this.primaryCamera;
    }

    public void setPrimaryCamera(BasicCamera primaryCamera) {
        this.primaryCamera = primaryCamera;
    }

    public BasicCamera getSecondaryCamera() {
        return this.secondaryCamera;
    }

    public void setSecondaryCamera(BasicCamera secondaryCamera) {
        this.secondaryCamera = secondaryCamera;
    }

    public Set<Lens> getLenses() {
        return this.lenses;
    }

    public void setLenses(Set<Lens> lenses) {
        this.lenses = lenses;
    }

    public Set<Accessory> getAccessories() {
        return this.accessories;
    }

    public void setAccessories(Set<Accessory> accessories) {
        this.accessories = accessories;
    }

    public void addAccessory(Accessory accessory){
        this.getAccessories().add(accessory);
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getCamera() {
        return this.primaryCamera.getMake() + " " + this.primaryCamera.getModel();
    }
}
