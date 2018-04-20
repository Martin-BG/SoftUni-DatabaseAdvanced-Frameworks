package user.exam.structure.employee;

import app.exam.domain.entities.Employee;
import app.exam.domain.entities.Position;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.ManyToOne;
import java.util.Arrays;

public class EmployeeHasPositionRelation {
    @Test
    public void testPositionRelation() throws Exception {
        long cnt = Arrays.stream(Employee.class.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(ManyToOne.class) && f.getType().equals(Position.class))
                .count();

        Assert.assertEquals(1, cnt);
    }
}
