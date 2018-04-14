package app.domain.dto;

import java.io.Serializable;

public class PhoneNumberDto implements Serializable {

    private String number;

    public PhoneNumberDto() {
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }
}
