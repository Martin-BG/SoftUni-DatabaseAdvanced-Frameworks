package user.exam.structure.employee;

import app.exam.domain.entities.Employee;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Arrays;

public class EmployeeHasID {
    @Test
    public void testHasIdentificator() {
        Field[] employeeFields = Employee.class.getDeclaredFields();
        Field id = Arrays.stream(employeeFields)
                .filter(field -> field.isAnnotationPresent(Id.class)).findFirst().get();
        Assert.assertNotNull(id);
    }
}
