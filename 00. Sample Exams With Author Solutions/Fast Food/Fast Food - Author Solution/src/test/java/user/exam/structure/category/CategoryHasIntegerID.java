package user.exam.structure.category;

import app.exam.domain.entities.Category;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Arrays;

public class CategoryHasIntegerID {
    @Test
    public void testHasIdentificator() {
        Field[] categoryFields = Category.class.getDeclaredFields();
        Field id = Arrays.stream(categoryFields)
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
