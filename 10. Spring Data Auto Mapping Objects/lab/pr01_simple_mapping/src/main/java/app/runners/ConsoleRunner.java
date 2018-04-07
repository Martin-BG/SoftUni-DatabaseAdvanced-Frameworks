package app.runners;

import app.dto.EmployeeDto;
import app.dto.EmployeeWithManagerDto;
import app.dto.ManagerDto;
import app.entities.Address;
import app.entities.Employee;
import app.repositories.AddressRepository;
import app.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class ConsoleRunner implements CommandLineRunner {

    private static final String INPUT_SEPARATOR = "\\s+";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public ConsoleRunner(final EmployeeRepository employeeRepository,
                         final AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(final String... args) {
        Address address = new Address();
        address.setCity("Sofia");
        address.setCountry("Bulgaria");

        Employee employee = new Employee();
        employee.setFirstName("Pesho");
        employee.setLastName("Peshev");
        employee.setSalary(BigDecimal.valueOf(1500));
        employee.setBirthday(LocalDate.parse("1980-01-01"));
        employee.setAddress(address);
        employee.setOnHoliday(false);

        Employee employee2 = new Employee();
        employee2.setFirstName("Tosho");
        employee2.setLastName("Toshev");
        employee2.setSalary(BigDecimal.valueOf(2000));
        employee2.setBirthday(LocalDate.parse("1987-01-01"));
        employee2.setAddress(address);
        employee2.setOnHoliday(true);

        Employee employee3 = new Employee();
        employee3.setFirstName("Koko");
        employee3.setLastName("Koshev");
        employee3.setSalary(BigDecimal.valueOf(1999.99));
        employee3.setBirthday(LocalDate.parse("1968-01-01"));
        employee3.setAddress(address);
        employee3.setOnHoliday(false);

        Employee manager = new Employee();
        manager.setFirstName("Gosho");
        manager.setLastName("Geshev");
        manager.setSalary(BigDecimal.valueOf(4000));
        manager.setBirthday(LocalDate.parse("1960-01-01"));
        manager.setAddress(address);
        manager.setOnHoliday(false);

        manager.getEmployees().add(employee);
        manager.getEmployees().add(employee2);
        manager.getEmployees().add(employee3);
        employee.setManager(manager);
        employee2.setManager(manager);
        employee3.setManager(manager);

        this.employeeRepository.save(manager);

        employee = this.employeeRepository.findById(3L).orElse(null);
        ModelMapper mapper = new ModelMapper();
        EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
        System.out.println(employeeDto);

        employee = this.employeeRepository.findById(1L).orElse(null);
        ManagerDto managerDto = mapper.map(employee, ManagerDto.class);
        System.out.println(managerDto);

        mapper.addMappings(new PropertyMap<Employee, EmployeeWithManagerDto>() {
            @Override
            protected void configure() {
                map().setManagerLastName(source.getManager().getLastName());
            }
        });

        this.employeeRepository
                .findAllByBirthdayBeforeOrderBySalaryDesc(LocalDate.parse("1990-01-01"))
                .stream()
                .map(emp -> mapper.map(emp, EmployeeWithManagerDto.class))
                .forEach(System.out::println);
    }
}
