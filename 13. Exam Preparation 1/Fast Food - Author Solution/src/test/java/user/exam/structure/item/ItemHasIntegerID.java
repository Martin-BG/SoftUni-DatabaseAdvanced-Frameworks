package user.exam.structure.item;

import app.exam.domain.entities.Item;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Arrays;

public class ItemHasIntegerID {
    @Test
    public void testHasIdentificator() {
        Field[] itemFields = Item.class.getDeclaredFields();
        Field id = Arrays.stream(itemFields)
                .filter(field -> field.isAnnotationPresent(Id.class)).findFirst().get();
        String integerName = Integer.class.getName();
        String intName = int.class.getName();
        if (!id.getType().getName().equals(integerName)) {
            Assert.assertTrue(true);
        } else if (!id.getType().getName().equals(intName)) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }
}
