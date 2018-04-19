package car_dealer.terminal;

import car_dealer.model.dto.binding.CarDto;
import car_dealer.model.dto.binding.CustomerDto;
import car_dealer.model.dto.binding.PartDto;
import car_dealer.model.dto.binding.SupplierDto;
import car_dealer.model.dto.binding.xml.CarsCarDto;
import car_dealer.model.dto.binding.xml.CustomersCustomerDto;
import car_dealer.model.dto.binding.xml.PartsPartDto;
import car_dealer.model.dto.binding.xml.SuppliersSupplierDto;
import car_dealer.model.dto.view.*;
import car_dealer.model.dto.view.xml.*;
import car_dealer.persistance.service.impl.*;
import car_dealer.utils.JsonParser;
import car_dealer.utils.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.List;

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
    private static final String XML_IN_SUPPLIERS_XML = RESOURCES_PATH + "xml/in/suppliers.xml";
    private static final String XML_IN_CUSTOMERS_XML = RESOURCES_PATH + "xml/in/customers.xml";
    private static final String XML_IN_PARTS_XML = RESOURCES_PATH + "xml/in/parts.xml";
    private static final String XML_IN_CARS_XML = RESOURCES_PATH + "xml/in/cars.xml";
    private static final String XML_OUT_ORDERED_CUSTOMERS_XML = RESOURCES_PATH + "xml/out/ordered-customers.xml";
    private static final String XML_OUT_TOYOTA_CARS_XML = RESOURCES_PATH + "xml/out/toyota-cars.xml";
    private static final String XML_OUT_LOCAL_SUPPLIERS_XML = RESOURCES_PATH + "xml/out/local-suppliers.xml";
    private static final String XML_OUT_CARS_AND_PARTS_XML = RESOURCES_PATH + "xml/out/cars-and-parts.xml";
    private static final String XML_OUT_CUSTOMERS_TOTAL_SALES_XML = RESOURCES_PATH + "xml/out/customers-total-sales.xml";
    private static final String XML_OUT_SALES_DISCOUNTS_XML = RESOURCES_PATH + "xml/out/sales-discounts.xml";

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
//        this.jsonSolution();

        this.xmlSolution();
    }

    private void xmlSolution() {
        this.seedDatabaseXml();

        this.getAllCustomersOrderedByBirthdayXml();

        this.getCarsFromMakeXml("Toyota");

        this.getLocalSuppliersXml();

        this.getAllCarsWithTheirPartsXml();

        this.getAllCustomersWithPurchasesXml();

        this.getAllSaleDetailsXml();
    }

    private void getAllSaleDetailsXml() {
        final List<SaleDetailsViewDto> salesDetails = this.saleService.getSalesDetails();
        SalesSaleDetailsViewDto salesDto = new SalesSaleDetailsViewDto();
        salesDto.setSales(salesDetails);
        this.xmlParser.objectToFile(salesDto, XML_OUT_SALES_DISCOUNTS_XML);
    }

    private void getAllCustomersWithPurchasesXml() {
        final List<CustomerPurchasesViewDto> customersPurchases = this.customerService.getCustomersPurchases();
        CustomersCustomerPurchasesViewDto customersListDto = new CustomersCustomerPurchasesViewDto();
        customersListDto.setCustomers(customersPurchases);
        this.xmlParser.objectToFile(customersListDto, XML_OUT_CUSTOMERS_TOTAL_SALES_XML);
    }

    private void getAllCarsWithTheirPartsXml() {
        this.xmlParser.objectToFile(this.carService.getAllCarsWithTheirPartsXml(), XML_OUT_CARS_AND_PARTS_XML);
    }

    private void getLocalSuppliersXml() {
        final List<SupplierViewDto> localSuppliers = this.supplierService.getLocalSuppliers();
        SuppliersSupplierViewDto suppliers = new SuppliersSupplierViewDto();
        suppliers.setSuppliers(localSuppliers);
        this.xmlParser.objectToFile(suppliers, XML_OUT_LOCAL_SUPPLIERS_XML);
    }

    private void getCarsFromMakeXml(final String make) {
        final List<CarViewDto> carsByMake = this.carService.getCarsByMake(make);
        CarsCarViewDto cars = new CarsCarViewDto();
        cars.setCars(carsByMake);
        this.xmlParser.objectToFile(cars, XML_OUT_TOYOTA_CARS_XML);
    }

    private void getAllCustomersOrderedByBirthdayXml() {
        final List<CustomerByBirthdayDto> allSortedByBirthday = this.customerService.getAllSortedByBirthday();
        CustomersCustomerByBirthdayDto customerDto = new CustomersCustomerByBirthdayDto();
        customerDto.setCustomers(allSortedByBirthday);
        this.xmlParser.objectToFile(customerDto, XML_OUT_ORDERED_CUSTOMERS_XML);
    }

    @Transactional
    protected void seedDatabaseXml() {
        SuppliersSupplierDto supplierDtos = this.xmlParser.objectFromFile(SuppliersSupplierDto.class, XML_IN_SUPPLIERS_XML);
        this.supplierService.saveAll(supplierDtos.getSuppliers().toArray(new SupplierDto[0]));

        CustomersCustomerDto customerDtos = this.xmlParser.objectFromFile(CustomersCustomerDto.class, XML_IN_CUSTOMERS_XML);
        this.customerService.saveAll(customerDtos.getCustomers().toArray(new CustomerDto[0]));

        PartsPartDto partDtos = this.xmlParser.objectFromFile(PartsPartDto.class, XML_IN_PARTS_XML);
        this.partService.saveAll(partDtos.getParts().toArray(new PartDto[0]));

        CarsCarDto carDtos = this.xmlParser.objectFromFile(CarsCarDto.class, XML_IN_CARS_XML);
        this.carService.saveAll(carDtos.getCars().toArray(new CarDto[0]));

        this.saleService.generateSales();
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
    protected void seedDatabaseJson() {
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
