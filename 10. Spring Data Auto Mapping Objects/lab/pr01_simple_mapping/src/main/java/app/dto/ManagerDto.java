package app.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ManagerDto {

    private String firstName;
    private String lastName;
    private Set<EmployeeDto> employees;

    public ManagerDto() {
        this.employees = new HashSet<>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Set<EmployeeDto> getEmployees() {
        return this.employees;
    }

    public void setEmployees(final Set<EmployeeDto> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return String.format("%s %s | Employees: %d%s",
                this.firstName, this.lastName, this.employees.size(),
                this.employees
                        .stream()
                        .map(employee -> String.format("%n    %s", employee))
                        .collect(Collectors.joining("")));
    }
}
