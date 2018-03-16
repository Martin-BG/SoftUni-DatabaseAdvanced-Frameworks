package pr03_car_shop_extend.contracts;

public interface Rentable extends Car {

    int getMinRentDay();

    Double getPricePerDay();

}
