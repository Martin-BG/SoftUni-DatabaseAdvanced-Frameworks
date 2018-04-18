package car_dealer.persistance.service.impl;

import car_dealer.model.dto.binding.CarDto;
import car_dealer.model.dto.view.CarPartsViewDto;
import car_dealer.model.dto.view.CarViewDto;
import car_dealer.model.dto.view.CarViewShortDto;
import car_dealer.model.dto.view.PartViewDto;
import car_dealer.model.entity.Car;
import car_dealer.model.entity.Part;
import car_dealer.persistance.repository.CarRepository;
import car_dealer.persistance.service.api.CarService;
import car_dealer.persistance.service.api.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final PartService partService;

    @Autowired
    public CarServiceImpl(final CarRepository carRepository,
                          final ModelMapper modelMapper,
                          final PartService partService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.partService = partService;
    }

    @Override
    public void saveAll(final CarDto[] carDtos) {
        final Car[] cars = this.modelMapper.map(carDtos, Car[].class);

        final List<Part> allParts = this.partService.getAllParts();
        final Random random = new Random();

        for (final Car car : cars) {
            do {
                car.getParts().add(allParts.get(random.nextInt(allParts.size())));
                if (random.nextInt(5) == 0 && car.getParts().size() >= 10) {
                    break;
                }
            } while (car.getParts().size() <= 20);
        }

        this.carRepository.saveAll(Arrays.asList(cars));
    }

    @Override
    public List<CarViewDto> getCarsByMake(final String make) {
        return this.carRepository
                .findByMakeOrderByModelAscTravelledDistanceDesc(make)
                .stream()
                .map(car -> this.modelMapper.map(car, CarViewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> getAllCars() {
        return this.carRepository.findAll();
    }

    @Override
    public List<CarPartsViewDto> getAllCarsWithTheirParts() {
        return this.carRepository
                .findAll()
                .stream()
                .map(car -> {
                    CarPartsViewDto carView = new CarPartsViewDto();
                    carView.setCar(this.modelMapper.map(car, CarViewShortDto.class));
                    carView.setParts(car
                            .getParts()
                            .stream()
                            .map(part -> this.modelMapper.map(part, PartViewDto.class))
                            .collect(Collectors.toSet())
                    );
                    return carView;
                })
                .collect(Collectors.toList());
    }
}
