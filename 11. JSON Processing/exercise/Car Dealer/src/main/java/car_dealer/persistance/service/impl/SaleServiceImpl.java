package car_dealer.persistance.service.impl;

import car_dealer.model.dto.view.CarViewShortDto;
import car_dealer.model.dto.view.SaleDetailsViewDto;
import car_dealer.model.entity.Car;
import car_dealer.model.entity.Customer;
import car_dealer.model.entity.Part;
import car_dealer.model.entity.Sale;
import car_dealer.persistance.repository.SaleRepository;
import car_dealer.persistance.service.api.CarService;
import car_dealer.persistance.service.api.CustomerService;
import car_dealer.persistance.service.api.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final CarService carService;
    private final CustomerService customerService;

    @Autowired
    public SaleServiceImpl(final SaleRepository saleRepository,
                           final ModelMapper modelMapper,
                           final CarService carService,
                           final CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public void generateSales() {
        final List<Car> cars = this.carService.getAllCars();
        final List<Customer> customers = this.customerService.getAllCustomers();
        final Random random = new Random();
        final Double[] discounts = {0d, 0.05d, 0.1d, 0.15d, 0.2d, 0.3d, 0.4d, 0.5d};

        List<Sale> sales = new LinkedList<>();

        for (final Car car : cars) {
            Sale sale = new Sale();
            sale.setCar(car);
            sale.setCustomer(customers.get(random.nextInt(customers.size())));
            sale.setDiscount(discounts[random.nextInt(discounts.length)]);
            this.saleRepository.saveAndFlush(sale);
            sales.add(sale);
        }

        this.saleRepository.saveAll(sales);
    }

    @Override
    public List<SaleDetailsViewDto> getSalesDetails() {
        return this.saleRepository
                .findAll()
                .stream()
                .map(sale -> {
                    SaleDetailsViewDto saleDto = new SaleDetailsViewDto();
                    saleDto.setCar(this.modelMapper.map(sale.getCar(), CarViewShortDto.class));
                    saleDto.setCustomerName(sale.getCustomer().getName());
                    saleDto.setDiscount(sale.getDiscount());

                    saleDto.setPrice(sale
                            .getCar()
                            .getParts()
                            .stream()
                            .map(Part::getPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add));

                    saleDto.setPriceWithDiscount(saleDto
                            .getPrice()
                            .multiply(BigDecimal.valueOf(1.0d - saleDto.getDiscount())));
                    return saleDto;
                })
                .collect(Collectors.toList());
    }
}
