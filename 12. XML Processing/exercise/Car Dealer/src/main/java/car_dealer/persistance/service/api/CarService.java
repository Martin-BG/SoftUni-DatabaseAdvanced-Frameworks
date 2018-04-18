package car_dealer.persistance.service.api;

import car_dealer.model.dto.binding.CarDto;
import car_dealer.model.dto.view.CarPartsViewDto;
import car_dealer.model.dto.view.CarViewDto;
import car_dealer.model.entity.Car;

import java.util.List;

public interface CarService {

    void saveAll(CarDto[] carDtos);

    List<Car> getAllCars();

    List<CarViewDto> getCarsByMake(String toyota);

    List<CarPartsViewDto> getAllCarsWithTheirParts();
}

