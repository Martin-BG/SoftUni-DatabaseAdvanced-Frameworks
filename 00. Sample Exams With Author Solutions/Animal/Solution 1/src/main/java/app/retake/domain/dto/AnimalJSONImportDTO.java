package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class AnimalJSONImportDTO implements Serializable {

    @Size(min = 3, max = 20)
    @Expose
    private String name;
    @Size(min = 3, max = 20)
    @Expose
    private String type;
    @Min(1)
    @Expose
    private Integer age;
    @Valid
    @Expose
    private PassportJSONImportDTO passport;

    public AnimalJSONImportDTO() {
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public PassportJSONImportDTO getPassport() {
        return this.passport;
    }

    public void setPassport(PassportJSONImportDTO passport) {
        this.passport = passport;
    }
}
