package hiberspring.services.impl;

import hiberspring.dtos.imports.employee.EmployeeImportFromXmlDto;
import hiberspring.dtos.views.EmployeeProductiveViewDto;
import hiberspring.entities.Employee;
import hiberspring.entities.EmployeeCard;
import hiberspring.repositories.BranchRepository;
import hiberspring.repositories.EmployeeCardRepository;
import hiberspring.repositories.EmployeeRepository;
import hiberspring.services.EmployeeService;
import hiberspring.utilities.MapperConverter;
import hiberspring.validators.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final EmployeeCardRepository employeeCardRepository;
    private final MapperConverter mapperConverter;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, EmployeeCardRepository employeeCardRepository, MapperConverter mapperConverter) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.mapperConverter = mapperConverter;
    }

    @Override
    public Employee createOne(EmployeeImportFromXmlDto employeeImportFromXmlDto) {
        Employee employee = this.mapperConverter.convert(employeeImportFromXmlDto, Employee.class);
        if (employee.getBranch() != null) {
            employee.setBranch(this.branchRepository.getBranchByName(employee.getBranch().getName()));
        }

        EmployeeCard employeeCard = null;
        if (employee.getCard() != null && this.employeeRepository.getEmployeeCountByEmployeeCardNumber(employee.getCard().getNumber()) == 0) {
            employeeCard = this.employeeCardRepository.getEmployeeCardByNumber(employee.getCard().getNumber());
        }
        employee.setCard(employeeCard);

        if (EntityValidator.isValid(employee)) {
            employee = this.employeeRepository.save(employee);
            return employee;
        }
        return null;
    }

    @Override
    public List<EmployeeProductiveViewDto> getProductiveEmployees() {
        List<Employee> productiveEmployees = this.employeeRepository.getProductiveEmployees();
        EmployeeProductiveViewDto[] employeeProductiveViewDtos = this.mapperConverter.convert(productiveEmployees, EmployeeProductiveViewDto[].class);
        return Arrays.asList(employeeProductiveViewDtos);
    }

}
