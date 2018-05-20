package com.photographyworkshops.domain.dto.lens;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LenImportJSONDto implements Serializable {

    @Expose
    private String make;

    @Expose
    private int focalLength;

    @Expose
    private float maxAperture;

    @SerializedName(value = "compatibleWith")
    @Expose
    private String compatibleMake;

    public LenImportJSONDto() {
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
}
