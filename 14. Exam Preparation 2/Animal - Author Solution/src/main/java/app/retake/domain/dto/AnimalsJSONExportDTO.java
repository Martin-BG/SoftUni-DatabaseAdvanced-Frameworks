package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;

public class AnimalsJSONExportDTO implements Serializable {

    @Expose
    private String ownerName;
    @Expose
    private String animalName;
    @Expose
    private Integer age;
    @Expose
    private String serialNumber;
    @Expose
    private Date registeredOn;

    public AnimalsJSONExportDTO() {
    }

    public AnimalsJSONExportDTO(String ownerName, String animalName, Integer age, String serialNumber, Date registeredOn) {
        this.ownerName = ownerName;
        this.animalName = animalName;
        this.age = age;
        this.serialNumber = serialNumber;
        this.registeredOn = registeredOn;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAnimalName() {
        return this.animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getRegisteredOn() {
        return this.registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }
}
