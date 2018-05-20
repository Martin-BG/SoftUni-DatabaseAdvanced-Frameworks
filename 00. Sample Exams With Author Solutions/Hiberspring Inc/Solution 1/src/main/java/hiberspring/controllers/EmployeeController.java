package hiberspring.controllers;

import hiberspring.constants.MessageConstants;
import hiberspring.dtos.imports.employee.EmployeeImportFromXmlDto;
import hiberspring.dtos.imports.employee.EmployeesImportFromXmlDto;
import hiberspring.dtos.views.EmployeeProductiveViewDto;
import hiberspring.entities.Employee;
import hiberspring.io.Writer;
import hiberspring.serializers.Serializer;
import hiberspring.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EmployeeController {
    private static final String EMPLOYEES_XML_INPUT_PATH = "/xml/input/employees.xml";
    private static final String EMPLOYEES_PRODUCTIVE_JSON_OUTPUT_PATH = "/src/main/resources/json/output/productive-employees.json";

    private final Serializer jsonSerializer;
    private final Serializer xmlSerializer;
    private final EmployeeService employeeService;
    private final Writer writer;

    @Autowired
    public EmployeeController(@Qualifier("json") Serializer jsonSerializer, @Qualifier("xml") Serializer xmlSerializer, EmployeeService employeeService, Writer writer) {
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
        this.employeeService = employeeService;
        this.writer = writer;
    }

    public void importEmployees() {
        EmployeesImportFromXmlDto employeesImportFromXmlDto = this.xmlSerializer.deserialize(EmployeesImportFromXmlDto.class, EMPLOYEES_XML_INPUT_PATH);
        for (EmployeeImportFromXmlDto employeeImportFromXmlDto : employeesImportFromXmlDto.getEmployeeImportFromXmlDtos()) {
            Employee employee = this.employeeService.createOne(employeeImportFromXmlDto);
            if (employee != null) {
                this.writer.println(MessageConstants.SUCCESSFULLY_IMPORTED_ENTITY_MESSAGE, employee.getClass().getSimpleName(), employee.getFullName());
            } else {
                this.writer.println(MessageConstants.INVALID_INPUT_DATA_MESSAGE);
            }
        }
    }

    public void exportProductiveEmployees() {
        List<EmployeeProductiveViewDto> employeeProductiveViewDtos =  this.employeeService.getProductiveEmployees();
        this.jsonSerializer.serialize(employeeProductiveViewDtos, EMPLOYEES_PRODUCTIVE_JSON_OUTPUT_PATH);
    }
}
