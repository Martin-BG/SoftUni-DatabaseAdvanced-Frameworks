package app.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "cameras")
@DiscriminatorColumn(name = "camera_type")
public abstract class BasicCamera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotNull
    @Column(name = "make")
    private String make;

    @NotNull
    @Column(name = "model")
    private String model;

    @Column(name = "is_full_frame")
    private boolean isFullFrame;

    @NotNull
    @Min(100)
    @Column(name = "min_iso", nullable = false)
    private int minISO = 100;

    @Column(name = "max_iso")
    private Integer maxISO;

    @OneToMany(mappedBy = "secondaryCamera")
    private Set<Photographer> secondaryPhotographer;

    @OneToMany(mappedBy = "primaryCamera")
    private Set<Photographer> primaryPhotographer;

    protected BasicCamera() {
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

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean getIsFullFrame() {
        return this.isFullFrame;
    }

    public void setIsFullFrame(boolean isFullFrame) {
        this.isFullFrame = isFullFrame;
    }

    public int getMinISO() {
        return this.minISO;
    }

    public void setMinISO(int minISO) {
        this.minISO = minISO;
    }

    public Integer getMaxISO() {
        return this.maxISO;
    }

    public void setMaxISO(Integer maxISO) {
        this.maxISO = maxISO;
    }

    protected abstract String type();

    public boolean isFullFrame() {
        return isFullFrame;
    }

    public void setFullFrame(boolean fullFrame) {
        isFullFrame = fullFrame;
    }

    public Set<Photographer> getSecondaryPhotographer() {
        return secondaryPhotographer;
    }

    public void setSecondaryPhotographer(Set<Photographer> secondaryPhotographer) {
        this.secondaryPhotographer = secondaryPhotographer;
    }

    public Set<Photographer> getPrimaryPhotographer() {
        return primaryPhotographer;
    }

    public void setPrimaryPhotographer(Set<Photographer> primaryPhotographer) {
        this.primaryPhotographer = primaryPhotographer;
    }

    @Override
    public String toString() {
        return type() + " " + make + " " + model;
    }
}
