package pr02_car_shop.model;

import pr02_car_shop.contracts.Car;

import java.io.Serializable;

public class Seat implements Car, Serializable {

    private final String model;
    private final String color;
    private final int horsePower;
    private final String countryProduced;

    public Seat(String model, String color, Integer horsePower, String countryProduced) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.countryProduced = countryProduced;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires",
                this.getModel(), this.countryProduced, TYRES);
    }
}
