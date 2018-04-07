package app.dto;

import java.math.BigDecimal;

public class EmployeeWithManagerDto {

    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private String managerLastName;

    public EmployeeWithManagerDto() {
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

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(final BigDecimal salary) {
        this.salary = salary;
    }

    public String getManagerLastName() {
        return this.managerLastName;
    }

    public void setManagerLastName(final String managerLastName) {
        this.managerLastName = managerLastName;
    }

    @Override
    public String toString() {
        return String.format("%s %s $%.2f - Manager: %s",
                this.firstName, this.lastName, this.salary,
                this.managerLastName != null ? this.managerLastName : "[no manager]");
    }
}
