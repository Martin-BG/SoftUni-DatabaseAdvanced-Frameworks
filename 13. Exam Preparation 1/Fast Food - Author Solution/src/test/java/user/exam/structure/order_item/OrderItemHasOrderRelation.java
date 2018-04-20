package user.exam.structure.order_item;

import app.exam.domain.entities.Order;
import app.exam.domain.entities.OrderItem;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.ManyToOne;
import java.util.Arrays;

public class OrderItemHasOrderRelation {
    @Test
    public void testPositionRelation() throws Exception {
        long cnt = Arrays.stream(OrderItem.class.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(ManyToOne.class)
                        && f.getType().equals(Order.class))
                .count();

        Assert.assertEquals(1, cnt);
    }
}
