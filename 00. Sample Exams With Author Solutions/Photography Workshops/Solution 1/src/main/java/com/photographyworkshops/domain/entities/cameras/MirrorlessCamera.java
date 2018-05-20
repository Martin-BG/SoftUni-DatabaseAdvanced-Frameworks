package com.photographyworkshops.domain.entities.cameras;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class MirrorlessCamera extends BasicCamera {

    @Column(name = "max_video_resolution")
    private String maxVideoResolution;

    @Column(name = "max_frame_rate")
    private int maxFrameRate;

    public MirrorlessCamera() {
    }

    public String getMaxVideoResolution() {
        return this.maxVideoResolution;
    }

    public void setMaxVideoResolution(String maxVideoResolution) {
        this.maxVideoResolution = maxVideoResolution;
    }

    public int getMaxFrameRate() {
        return this.maxFrameRate;
    }

    public void setMaxFrameRate(int maxFrameRate) {
        this.maxFrameRate = maxFrameRate;
    }
}
