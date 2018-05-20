package user.exam.structure.order_item;

import app.exam.domain.entities.OrderItem;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class OrderItemHasFieldsCount {
    @Test
    public void testFieldsCount() {
        Field[] itemFields = OrderItem.class.getDeclaredFields();
        Assert.assertTrue("OrderItem fields count is not correct! " +
                "Fields count should be at least 3.", itemFields.length >= 3);
    }
}
