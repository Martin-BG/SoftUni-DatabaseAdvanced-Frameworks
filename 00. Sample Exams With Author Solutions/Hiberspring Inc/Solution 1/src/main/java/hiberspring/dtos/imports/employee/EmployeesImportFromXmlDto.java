package hiberspring.dtos.imports.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesImportFromXmlDto {

    @XmlElement(name = "employee")
    private List<EmployeeImportFromXmlDto> employeeImportFromXmlDtos;

    public EmployeesImportFromXmlDto() {
    }

    public List<EmployeeImportFromXmlDto> getEmployeeImportFromXmlDtos() {
        return this.employeeImportFromXmlDtos;
    }

    public void setEmployeeImportFromXmlDtos(List<EmployeeImportFromXmlDto> employeeImportFromXmlDtos) {
        this.employeeImportFromXmlDtos = employeeImportFromXmlDtos;
    }
}
