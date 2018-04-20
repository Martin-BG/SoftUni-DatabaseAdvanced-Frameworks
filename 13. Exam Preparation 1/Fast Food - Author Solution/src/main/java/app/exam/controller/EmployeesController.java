package app.exam.controller;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    @Qualifier(value = "JSONParser")
    private Parser jsonParser;

    public String importDataFromJSON(String jsonContent) {
        EmployeeJSONImportDTO[] employeeJSONImportDTOS = null;
        try {
            employeeJSONImportDTOS = this.jsonParser.read(EmployeeJSONImportDTO[].class, jsonContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (EmployeeJSONImportDTO employeeJSONImportDTO : employeeJSONImportDTOS) {
            try {
                this.employeeService.create(employeeJSONImportDTO);
                sb.append(String.format("Record %s successfully imported.",
                        employeeJSONImportDTO.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
