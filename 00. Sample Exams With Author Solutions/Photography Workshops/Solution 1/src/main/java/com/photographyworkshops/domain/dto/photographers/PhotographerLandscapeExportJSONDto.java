package com.photographyworkshops.domain.dto.photographers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotographerLandscapeExportJSONDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private String primaryCameraMake;

    @SerializedName(value = "lensesCount")
    @Expose
    private long count;

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

    public String getPrimaryCameraMake() {
        return this.primaryCameraMake;
    }

    public void setPrimaryCameraMake(String primaryCameraMake) {
        this.primaryCameraMake = primaryCameraMake;
    }

    public long getCount() {
        return this.count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
