package user.exam.structure.category;

import app.exam.domain.entities.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryHasID {

    @Test
    public void testHasIdentificator() {
        Field[] categoryFields = Category.class.getDeclaredFields();
        Field id = Arrays.stream(categoryFields)
                .filter(field -> field.isAnnotationPresent(Id.class)).findFirst().get();
        Assert.assertNotNull(id);
    }
}