package car_dealer.terminal;

import car_dealer.model.dto.binding.CarDto;
import car_dealer.model.dto.binding.CustomerDto;
import car_dealer.model.dto.binding.PartDto;
import car_dealer.model.dto.binding.SupplierDto;
import car_dealer.persistance.service.impl.*;
import car_dealer.utils.JsonParser;
import car_dealer.utils.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;

@Controller
public class Terminal implements CommandLineRunner {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources/";
    private static final String SEED_SUPPLIERS_JSON = RESOURCES_PATH + "json/in/suppliers.json";
    private static final String SEED_PARTS_JSON = RESOURCES_PATH + "json/in/parts.json";
    private static final String SEED_CARS_JSON = RESOURCES_PATH + "json/in/cars.json";
    private static final String SEED_CUSTOMERS_JSON = RESOURCES_PATH + "json/in/customers.json";
    private static final String OUTPUT_ORDERED_CUSTOMERS_JSON = RESOURCES_PATH + "json/out/ordered-customers.json";
    private static final String OUTPUT_TOYOTA_CARS_JSON = RESOURCES_PATH + "json/out/toyota-cars.json";
    private static final String OUTPUT_LOCAL_SUPPLIERS_JSON = RESOURCES_PATH + "json/out/local-suppliers.json";
    private static final String OUTPUT_CARS_PARTS_JSON = RESOURCES_PATH + "json/out/cars-and-parts.json";
    private static final String OUTPUT_CUSTOMERS_PURCHASES_JSON = RESOURCES_PATH + "json/out/customers-total-sales.json";
    private static final String OUTPUT_SALES_DETAILS_JSON = RESOURCES_PATH + "json/out/sales-discounts.json";

    private final JsonParser jsonParser;
    private final XmlParser xmlParser;
    private final CarServiceImpl carService;
    private final CustomerServiceImpl customerService;
    private final PartServiceImpl partService;
    private final SaleServiceImpl saleService;
    private final SupplierServiceImpl supplierService;

    @Autowired
    public Terminal(final JsonParser jsonParser,
                    final XmlParser xmlParser,
                    final CarServiceImpl carService,
                    final CustomerServiceImpl customerService,
                    final PartServiceImpl partService,
                    final SaleServiceImpl saleService,
                    final SupplierServiceImpl supplierService) {

        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.supplierService = supplierService;
    }

    @Override
    public void run(final String... args) {
        jsonSolution();
    }

    private void jsonSolution() {
        this.seedDatabaseJson();

        this.getAllCustomersOrderedByBirthdayJson();

        this.getCarsFromMakeJson("Toyota");

        this.getLocalSuppliersJson();

        this.getAllCarsWithTheirPartsJson();

        this.getAllCustomersWithPurchasesJson();

        this.getAllSaleDetailsJson();
    }

    private void getAllSaleDetailsJson() {
        this.jsonParser.objectToFile(
                this.saleService.getSalesDetails(),
                OUTPUT_SALES_DETAILS_JSON);
    }

    private void getAllCustomersWithPurchasesJson() {
        this.jsonParser.objectToFile(
                this.customerService.getCustomersPurchases(),
                OUTPUT_CUSTOMERS_PURCHASES_JSON);
    }

    private void getAllCarsWithTheirPartsJson() {
        this.jsonParser.objectToFile(
                this.carService.getAllCarsWithTheirParts(),
                OUTPUT_CARS_PARTS_JSON);
    }

    private void getLocalSuppliersJson() {
        this.jsonParser.objectToFile(
                this.supplierService.getLocalSuppliers(),
                OUTPUT_LOCAL_SUPPLIERS_JSON);
    }

    private void getCarsFromMakeJson(String make) {
        this.jsonParser.objectToFile(
                this.carService.getCarsByMake(make),
                OUTPUT_TOYOTA_CARS_JSON);
    }

    private void getAllCustomersOrderedByBirthdayJson() {
        this.jsonParser.objectToFile(
                this.customerService.getAllSortedByBirthday(),
                OUTPUT_ORDERED_CUSTOMERS_JSON);
    }

    @Transactional
    private void seedDatabaseJson() {
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
