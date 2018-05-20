package user.exam.structure.category;

import app.exam.domain.entities.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryHasFieldsCount {
    @Test
    public void testFieldsCount() {
        Field[] categoryFields = Category.class.getDeclaredFields();
        Assert.assertEquals("Category fields count is not correct! " +
                "Fields count should be 3.", 3, categoryFields.length);
    }
}
