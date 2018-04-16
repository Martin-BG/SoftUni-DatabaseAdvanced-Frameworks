package car_dealer.terminal;

import car_dealer.model.dto.binding.CarDto;
import car_dealer.model.dto.binding.CustomerDto;
import car_dealer.model.dto.binding.PartDto;
import car_dealer.model.dto.binding.SupplierDto;
import car_dealer.persistance.service.impl.*;
import car_dealer.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;

@Controller
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources/";
    private static final String SEED_SUPPLIERS_JSON = RESOURCES_PATH + "seed/suppliers.json";
    private static final String SEED_PARTS_JSON = RESOURCES_PATH + "seed/parts.json";
    private static final String SEED_CARS_JSON = RESOURCES_PATH + "seed/cars.json";
    private static final String SEED_CUSTOMERS_JSON = RESOURCES_PATH + "seed/customers.json";
    private static final String OUTPUT_ORDERED_CUSTOMERS_JSON = RESOURCES_PATH + "output/ordered-customers.json";
    private static final String OUTPUT_TOYOTA_CARS_JSON = RESOURCES_PATH + "output/toyota-cars.json";

    private final JsonParser jsonParser;
    private final CarServiceImpl carService;
    private final CustomerServiceImpl customerService;
    private final PartServiceImpl partService;
    private final SaleServiceImpl saleService;
    private final SupplierServiceImpl supplierService;

    @Autowired
    public Terminal(final JsonParser jsonParser,
                    final CarServiceImpl carService,
                    final CustomerServiceImpl customerService,
                    final PartServiceImpl partService,
                    final SaleServiceImpl saleService,
                    final SupplierServiceImpl supplierService) {

        this.jsonParser = jsonParser;
        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.supplierService = supplierService;
    }

    @Override
    public void run(final String... args) {
//        this.seedDatabase();

//        this.getAllCustomersOrderedByBirthday();

        this.getCarsFromMake("Toyota");

    }

    private void getCarsFromMake(String make) {
        this.jsonParser.objectToFile(
                this.carService.getCarsByMake(make),
                OUTPUT_TOYOTA_CARS_JSON);
    }

    private void getAllCustomersOrderedByBirthday() {
        this.jsonParser.objectToFile(
                this.customerService.getAllSortedByBirthday(),
                OUTPUT_ORDERED_CUSTOMERS_JSON);
    }

    private void seedDatabase() {
        SupplierDto[] supplierDtos = this.jsonParser.objectFromFile(SupplierDto[].class, SEED_SUPPLIERS_JSON);
        this.supplierService.saveAll(supplierDtos);

        CustomerDto[] customerDtos = this.jsonParser.objectFromFile(CustomerDto[].class, SEED_CUSTOMERS_JSON);
        this.customerService.saveAll(customerDtos);

        PartDto[] partDtos = this.jsonParser.objectFromFile(PartDto[].class, SEED_PARTS_JSON);
        this.partService.saveAll(partDtos);

        CarDto[] carDtos = this.jsonParser.objectFromFile(CarDto[].class, SEED_CARS_JSON);
        this.carService.saveAll(carDtos);

        this.saleService.generateSales();
    }

}
