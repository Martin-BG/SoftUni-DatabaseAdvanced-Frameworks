package pr03_ferrari.model;

import pr03_ferrari.constants.Constants;
import pr03_ferrari.contracts.Car;

public class AbstractCar implements Car {

    private String model;
    private String driver;

    public AbstractCar(String model, String driver) {
        this.model = model;
        this.driver = driver;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String useBrakes() {
        return Constants.USING_BRAKES;
    }

    @Override
    public String pushGas() {
        return Constants.PUSHING_GAS;
    }

    public String getDriver() {
        return this.driver;
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s/%s%n",
                this.getModel(), this.useBrakes(), this.pushGas(), this.getDriver());
    }
}
