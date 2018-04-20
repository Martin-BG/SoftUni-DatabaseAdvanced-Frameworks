package user.exam.structure.order;

import app.exam.domain.entities.Employee;
import app.exam.domain.entities.Order;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.ManyToOne;
import java.util.Arrays;

public class OrderHasEmployeeRelation {
    @Test
    public void testPositionRelation() throws Exception {
        long cnt = Arrays.stream(Order.class.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(ManyToOne.class)
                        && f.getType().equals(Employee.class))
                .count();

        Assert.assertEquals(1, cnt);
    }
}
