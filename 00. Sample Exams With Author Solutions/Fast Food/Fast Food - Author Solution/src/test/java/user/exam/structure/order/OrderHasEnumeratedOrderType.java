package user.exam.structure.order;

import app.exam.domain.entities.Order;
import app.exam.domain.entities.OrderType;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Enumerated;
import java.lang.reflect.Field;
import java.util.Arrays;

public class OrderHasEnumeratedOrderType {
    @Test
    public void testEnumeratedField() {
        Field[] orderFields = Order.class.getDeclaredFields();
        Field id = Arrays.stream(orderFields)
                .filter(field -> field.isAnnotationPresent(Enumerated.class)
                        && field.getType().equals(OrderType.class)).findFirst().get();
        Assert.assertNotNull(id);
    }
}
