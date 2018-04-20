package user.exam.structure.item;

import app.exam.domain.entities.Item;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.OneToMany;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemHasOrderItemsRelation {
    @Test
    public void testOrdersRelation() throws ClassNotFoundException {
        Field[] itemFields = Item.class.getDeclaredFields();
        List<Field> oneToManyFields = Arrays.stream(itemFields)
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
                    if (fieldValueName.equals(Item.class.getName())) {
                        count++;
                    }
                }
            }
        }
        Assert.assertEquals(1, count);
    }
}
