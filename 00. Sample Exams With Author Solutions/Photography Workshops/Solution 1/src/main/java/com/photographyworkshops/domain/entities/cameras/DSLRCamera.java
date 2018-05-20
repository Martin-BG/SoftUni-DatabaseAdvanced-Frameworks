package com.photographyworkshops.domain.entities.cameras;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DSLRCamera extends BasicCamera {

    @Column(name = "max_shutter_speed")
    private int maxShutterSpeed;

    public DSLRCamera() {
    }

    public int getMaxShutterSpeed() {
        return this.maxShutterSpeed;
    }

    public void setMaxShutterSpeed(int maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }
}
