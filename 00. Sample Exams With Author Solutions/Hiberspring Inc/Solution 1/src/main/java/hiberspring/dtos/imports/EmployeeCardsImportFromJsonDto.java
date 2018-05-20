package hiberspring.dtos.imports;

import com.google.gson.annotations.Expose;

public class EmployeeCardsImportFromJsonDto {

    @Expose
    private String number;

    public EmployeeCardsImportFromJsonDto() {
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
