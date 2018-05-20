package user.exam.data_import.items;

import app.exam.ExamApplication;
import app.exam.repository.ItemsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class AllItemsImportedCount {
    private static final int EXPECTED_ITEMS_COUNT = 34;
    @Autowired
    private ItemsRepository itemsRepository;

    @Test
    public void testItemsImportedCount() throws Exception {
        long count = this.itemsRepository
                .findAll()
                .spliterator()
                .getExactSizeIfKnown();
        Assert.assertEquals(EXPECTED_ITEMS_COUNT, count);
    }
}
