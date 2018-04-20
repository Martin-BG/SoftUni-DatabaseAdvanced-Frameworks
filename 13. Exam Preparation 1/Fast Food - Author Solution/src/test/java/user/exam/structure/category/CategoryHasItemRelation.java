package user.exam.structure.category;

import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.OneToMany;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryHasItemRelation {
    @Test
    public void testRelation() throws ClassNotFoundException {
        Field[] categoryFields = Category.class.getDeclaredFields();
        List<Field> oneToManyFields = Arrays.stream(categoryFields)
                .filter(field -> field.isAnnotationPresent(OneToMany.class))
                .collect(Collectors.toList());
        int count = 0;
        for (Field oneToManyField : oneToManyFields) {
            Type type = oneToManyField.getGenericType();
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                String iterableType = parameterizedType.getActualTypeArguments()[0].getTypeName();
                Class<?> inverseField = Class.forName(iterableType);
                for (Field field : inverseField.getDeclaredFields()) {
                    String fieldValueName = field.getType().getName();
                    if (fieldValueName.equals(Category.class.getName()) &&
                            iterableType.equals(Item.class.getName())) {
                        count++;
                    }
                }
            }
        }
        Assert.assertEquals(1, count);
    }
}
