package app.domain.dto;

import java.io.Serializable;

public class AddressDto implements Serializable {

    private String country;
    private String city;
    private String street;

    public AddressDto() {
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }
}
