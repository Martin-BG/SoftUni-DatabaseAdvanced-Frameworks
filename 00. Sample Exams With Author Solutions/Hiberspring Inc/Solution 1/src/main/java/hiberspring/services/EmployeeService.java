package hiberspring.services;

import hiberspring.dtos.imports.employee.EmployeeImportFromXmlDto;
import hiberspring.dtos.views.EmployeeProductiveViewDto;
import hiberspring.entities.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createOne(EmployeeImportFromXmlDto employeeImportFromXmlDto);

    List<EmployeeProductiveViewDto> getProductiveEmployees();
}
