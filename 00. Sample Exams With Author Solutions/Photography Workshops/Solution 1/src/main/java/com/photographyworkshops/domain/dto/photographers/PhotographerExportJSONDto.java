package com.photographyworkshops.domain.dto.photographers;

import com.google.gson.annotations.Expose;

public class PhotographerExportJSONDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private String phone;

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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
