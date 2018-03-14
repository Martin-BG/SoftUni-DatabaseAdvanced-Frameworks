package pr08_vehicles.model;

public class Car extends Vehicle {

    private static final double AIR_CONDITIONER_EXTRA_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumptionPerKm) {
        super(fuelQuantity, fuelConsumptionPerKm + AIR_CONDITIONER_EXTRA_CONSUMPTION);
    }

    @Override
    public void refuel(double fuel) {
        super.setFuelQuantity(super.getFuelQuantity() + fuel);
    }

    @Override
    public void drive(double distance) {
        double fuelNeeded = super.getFuelConsumptionPerKm() * distance;
        if (fuelNeeded > super.getFuelQuantity()) {
            throw new IllegalArgumentException("Car needs refueling");
        }
        super.setFuelQuantity(super.getFuelQuantity() - fuelNeeded);
    }
}
