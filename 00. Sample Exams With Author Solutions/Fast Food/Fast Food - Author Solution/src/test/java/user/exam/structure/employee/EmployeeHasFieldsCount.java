package user.exam.structure.employee;

import app.exam.domain.entities.Employee;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class EmployeeHasFieldsCount {
    @Test
    public void testFieldsCount() {
        Field[] employeeFields = Employee.class.getDeclaredFields();
        Assert.assertTrue("Employee fields count is not correct! " +
                "Fields count should be 3.", employeeFields.length >= 4);
    }
}
