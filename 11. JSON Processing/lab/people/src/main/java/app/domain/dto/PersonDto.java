package app.domain.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PersonDto implements Serializable {

    private String firstName;
    private String lastName;
    private AddressDto address;
    private Set<PhoneNumberDto> phoneNumbers;

    public PersonDto() {
        this.phoneNumbers = new HashSet<>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public AddressDto getAddress() {
        return this.address;
    }

    public void setAddress(final AddressDto address) {
        this.address = address;
    }

    public Set<PhoneNumberDto> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public void setPhoneNumbers(final Set<PhoneNumberDto> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
