package com.photographyworkshops.domain.dto.photographers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PhotographerImportJSONDto implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @SerializedName(value = "phone")
    @Expose
    private String phoneNumber;

    @SerializedName(value = "lenses")
    @Expose
    private List<Long> lenses;

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

    public List<Long> getLenses() {
        return this.lenses;
    }

    public void setLenses(List<Long> lenses) {
        this.lenses = lenses;
    }
}
