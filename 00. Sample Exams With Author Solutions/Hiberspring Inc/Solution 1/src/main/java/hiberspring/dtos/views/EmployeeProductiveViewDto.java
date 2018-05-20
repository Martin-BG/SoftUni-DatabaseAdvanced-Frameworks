package hiberspring.dtos.views;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeProductiveViewDto {

    @Expose
    @SerializedName("full_name")
    private String fullName;

    @Expose
    private String position;

    @Expose
    private String cardNumber;

    public EmployeeProductiveViewDto() {
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
