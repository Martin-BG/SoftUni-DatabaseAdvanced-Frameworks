package pr03_car_shop_extend.model;

import pr03_car_shop_extend.contracts.Sellable;

public class Seat extends AbstractCar implements Sellable {

    private final double price;

    public Seat(String model, String color, int horsePower, String countryProduced, double price) {
        super(model, color, horsePower, countryProduced);
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()
                + "Price: " + getPrice();
    }
}
