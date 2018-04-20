package user.exam.structure.position;

import app.exam.domain.entities.Item;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class PositionHasFieldsCount {
    @Test
    public void testFieldsCount() {
        Field[] positionFields = Item.class.getDeclaredFields();
        Assert.assertTrue("Position fields count is not correct! " +
                "Fields count should be at least 2.", positionFields.length >= 2);
    }
}

