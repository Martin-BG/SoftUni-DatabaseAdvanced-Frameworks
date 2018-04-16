package car_dealer.persistance.service.impl;

import car_dealer.model.entity.Car;
import car_dealer.model.entity.Customer;
import car_dealer.model.entity.Sale;
import car_dealer.persistance.repository.SaleRepository;
import car_dealer.persistance.service.api.CarService;
import car_dealer.persistance.service.api.CustomerService;
import car_dealer.persistance.service.api.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
            sales.add(sale);
        }

        this.saleRepository.saveAll(sales);
    }
}
