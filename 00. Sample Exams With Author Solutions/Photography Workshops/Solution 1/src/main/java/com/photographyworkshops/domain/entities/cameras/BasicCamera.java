package com.photographyworkshops.domain.entities.cameras;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "cameras")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BasicCamera implements Serializable {

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
    @Column(name = "min_iso")
    private int minISO;

    @Column(name = "max_iso")
    private int maxISO;

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

    public boolean getIsFullFrame(){
        return this.isFullFrame;
    }

    public void setIsFullFrame(boolean isFullFrame){
        this.isFullFrame = isFullFrame;
    }

    public int getMinISO() {
        return this.minISO;
    }

    public void setMinISO(int minISO) {
        this.minISO = minISO;
    }

    public int getMaxISO() {
        return this.maxISO;
    }

    public void setMaxISO(int maxISO) {
        this.maxISO = maxISO;
    }
}
