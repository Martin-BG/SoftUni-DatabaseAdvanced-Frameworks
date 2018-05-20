package app.model.entities;

import app.model.validation.Phone;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "photographers")
public class Photographer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Basic
    @Phone
    private String phone;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "primary_camera_id")
    private BasicCamera primaryCamera;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "secondary_camera_id")
    private BasicCamera secondaryCamera;

    @OneToMany(mappedBy = "owner")
    private List<Accessory> accessories;

    @OneToMany
    private Set<Workshop> trainerIn;
    @ManyToMany
    private Set<Workshop> participatesIn;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BasicCamera getPrimaryCamera() {
        return primaryCamera;
    }

    public void setPrimaryCamera(BasicCamera primaryCamera) {
        this.primaryCamera = primaryCamera;
    }

    public BasicCamera getSecondaryCamera() {
        return secondaryCamera;
    }

    public void setSecondaryCamera(BasicCamera secondaryCamera) {
        this.secondaryCamera = secondaryCamera;
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessory> accessories) {
        this.accessories = accessories;
    }

    public Set<Workshop> getTrainerIn() {
        return trainerIn;
    }

    public void setTrainerIn(Set<Workshop> trainerIn) {
        this.trainerIn = trainerIn;
    }

    public Set<Workshop> getParticipatesIn() {
        return participatesIn;
    }

    public void setParticipatesIn(Set<Workshop> participatesIn) {
        this.participatesIn = participatesIn;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
