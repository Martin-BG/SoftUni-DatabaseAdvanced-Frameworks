package car_dealer.persistance.service.api;

import car_dealer.model.dto.binding.CarDto;
import car_dealer.model.entity.Car;

import java.util.List;

public interface CarService {

    void saveAll(CarDto[] carDtos);

    List<Car> getAllCars();
}
