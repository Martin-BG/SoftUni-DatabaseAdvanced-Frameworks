package user.exam.structure.item;

import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.ManyToOne;
import java.util.Arrays;

public class ItemHasCategoryRelation {
    @Test
    public void testPositionRelation() throws Exception {
        long cnt = Arrays.stream(Item.class.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(ManyToOne.class) && f.getType().equals(Category.class))
                .count();

        Assert.assertEquals(1, cnt);
    }
}
