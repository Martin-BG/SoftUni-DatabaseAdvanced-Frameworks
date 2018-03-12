package pr03_car_shop_extend.model;

import pr03_car_shop_extend.contracts.Car;

public abstract class AbstractCar implements Car {

    private final String model;
    private final String color;
    private final int horsePower;
    private final String countryProduced;

    public AbstractCar(String model, String color, int horsePower, String countryProduced) {
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
    public String getCountryProduced() {
        return this.countryProduced;
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
        return String.format("This is %s produced in %s and has %d tires.",
                this.getModel(), this.getCountryProduced(), TIRES);
    }
}
