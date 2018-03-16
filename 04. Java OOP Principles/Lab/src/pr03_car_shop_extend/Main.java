package pr03_car_shop_extend;

import pr03_car_shop_extend.contracts.Car;
import pr03_car_shop_extend.contracts.Rentable;
import pr03_car_shop_extend.contracts.Sellable;
import pr03_car_shop_extend.model.Audi;
import pr03_car_shop_extend.model.Seat;

public class Main {


    public static void main(String[] args) {
        Sellable seat = new Seat("Leon", "Gray", 110, "Spain", 11111.1);
        Rentable audi = new Audi("Leon", "Gray", 110, "Spain", 3, 99.9);

        printCarInfo(seat);
        printCarInfo(audi);
    }

    private static void printCarInfo(Car car) {
        System.out.println(String.format(
                "%s is %s color and have %s horse power",
                car.getModel(),
                car.getColor(),
                car.getHorsePower()));
        System.out.println(car.toString());
    }
}
