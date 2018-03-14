package pr08_vehicles.model;

public abstract class Vehicle {

    private double fuelQuantity;
    private double fuelConsumptionPerKm;

    Vehicle(double fuelQuantity, double fuelConsumptionPerKm) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumptionPerKm(fuelConsumptionPerKm);
    }

    public final double getFuelQuantity() {
        return this.fuelQuantity;
    }

    void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    double getFuelConsumptionPerKm() {
        return this.fuelConsumptionPerKm;
    }

    private void setFuelConsumptionPerKm(double fuelConsumptionPerKm) {
        this.fuelConsumptionPerKm = fuelConsumptionPerKm;
    }

    public abstract void refuel(double fuel);

    public abstract void drive(double distance);
}
