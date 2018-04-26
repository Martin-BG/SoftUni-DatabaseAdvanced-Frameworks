package org.softuni.ruk.persistance.service.impl;

import org.softuni.ruk.config.Config;
import org.softuni.ruk.model.dto.binding.json.EmployeeFromJsonDto;
import org.softuni.ruk.model.dto.view.json.EmployeesWithClientsDto;
import org.softuni.ruk.model.entity.Branch;
import org.softuni.ruk.model.entity.Client;
import org.softuni.ruk.model.entity.Employee;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.persistance.repository.EmployeeRepository;
import org.softuni.ruk.persistance.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final ModelParser mapper;
    private final ValidationUtil validator;
    private final BranchServiceImpl branchService;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository repository,
                               final ModelParser mapper,
                               final ValidationUtil validator,
                               final BranchServiceImpl branchService) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
        this.branchService = branchService;
    }

    @Override
    public <T> String create(final T t) {
        EmployeeFromJsonDto dto = (EmployeeFromJsonDto) t;

        if (!this.validator.isValid(dto)) {
            return Config.ERROR_INCORRECT_DATA;
        }

        Branch branch = this.branchService.findOneByName(dto.getBranchName());
        String[] names = dto.getFullName().split("\\s+");

        if (names.length != 2 || branch == null
                || this.repository.findOneByFirstNameAndLastName(names[0], names[1]) != null) {
            return Config.ERROR_INCORRECT_DATA;
        }

        Employee employee = this.mapper.convert(dto, Employee.class);

        employee.setFirstName(names[0]);
        employee.setLastName(names[1]);
        employee.setBranch(branch);

        this.repository.saveAndFlush(employee);

        return String.format(Config.SUCCESSFULLY_IMPORTED, "Employee",
                String.format("%s %s", employee.getFirstName(), employee.getLastName()));
    }

    Employee findOneByFirstNameAndLastName(final String firstName, final String lastName) {
        return this.repository.findOneByFirstNameAndLastName(firstName, lastName);
    }

    public List<EmployeesWithClientsDto> getEmployeesByClients() {
        System.out.println();
        final List<Employee> employees = this.repository.getEmployeesByClients();

        return employees
                .stream()
                .map(employee -> {
                    EmployeesWithClientsDto dto = new EmployeesWithClientsDto();
                    dto.setSalary(employee.getSalary());
                    dto.setStartedOn(employee.getStartedOn());
                    dto.setFullName(String.format("%s %s", employee.getFirstName(), employee.getLastName()));
                    dto.setClients(employee
                            .getClients()
                            .stream()
                            .map(Client::getFullName)
                            .collect(Collectors.toList()));
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
