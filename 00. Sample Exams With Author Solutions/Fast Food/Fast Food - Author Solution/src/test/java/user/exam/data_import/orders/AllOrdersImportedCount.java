package user.exam.data_import.orders;

import app.exam.ExamApplication;
import app.exam.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class AllOrdersImportedCount {
    private static final int EXPECTED_ORDERS_COUNT = 20;
    @Autowired
    private OrderRepository orderRepository;

    @Before
    @Transactional
    public void insertEmployeesAndItems() {

    }

    @Test
    public void testItemsImportedCount() throws Exception {
        long count = this.orderRepository
                .findAll()
                .spliterator()
                .getExactSizeIfKnown();
        this.orderRepository.deleteAll();
        this.orderRepository.flush();
        Assert.assertEquals(EXPECTED_ORDERS_COUNT, count);
    }
}
