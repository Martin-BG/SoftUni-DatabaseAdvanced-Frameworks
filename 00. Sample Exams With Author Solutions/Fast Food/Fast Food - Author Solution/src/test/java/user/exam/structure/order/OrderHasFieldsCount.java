package user.exam.structure.order;

import app.exam.domain.entities.Item;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class OrderHasFieldsCount {
    @Test
    public void testFieldsCount() {
        Field[] itemFields = Item.class.getDeclaredFields();
        Assert.assertTrue("Item fields count is not correct! " +
                "Fields count should be 3.", itemFields.length >= 5);
    }
}
