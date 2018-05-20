package user.exam.data_import.items;

import app.exam.ExamApplication;
import app.exam.config.Config;
import app.exam.controller.ItemsController;
import app.exam.io.interfaces.FileIO;
import app.exam.repository.ItemsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class AllItemsImportedOutput {
    private static final String EXPECTED_INSERTION_STRING = "Error: Invalid data.\n" +
            "Error: Invalid data.\n" +
            "Error: Invalid data.\n" +
            "Error: Invalid data.\n" +
            "Error: Invalid data.\n" +
            "Error: Invalid data.\n" +
            "Record Hamburger successfully imported.\n" +
            "Error: Invalid data.\n" +
            "Record Cheeseburger successfully imported.\n" +
            "Record Quarter Pounder successfully imported.\n" +
            "Record Double Cheeseburger successfully imported.\n" +
            "Record Daily Double successfully imported.\n" +
            "Record Ranger Burger successfully imported.\n" +
            "Record BBQ Burger successfully imported.\n" +
            "Record Bacon Deluxe successfully imported.\n" +
            "Record Triple Cheeseburger successfully imported.\n" +
            "Record Premium chicken sandwich successfully imported.\n" +
            "Record Snack Wrap successfully imported.\n" +
            "Record Premium Chicken Wrap successfully imported.\n" +
            "Record Chicken Nuggets successfully imported.\n" +
            "Record Crispy Chicken Deluxe successfully imported.\n" +
            "Record Grilled Chicken Deluxe successfully imported.\n" +
            "Record Tasty Basket successfully imported.\n" +
            "Record Chicken Tenders successfully imported.\n" +
            "Record Fries successfully imported.\n" +
            "Record Crispy Fries successfully imported.\n" +
            "Record Curly Fries successfully imported.\n" +
            "Record Cola successfully imported.\n" +
            "Record Orange Drink successfully imported.\n" +
            "Record Purple Drink successfully imported.\n" +
            "Record Ice Cream successfully imported.\n" +
            "Record Cookie successfully imported.\n" +
            "Record Cake successfully imported.\n" +
            "Record Cesar Salad successfully imported.\n" +
            "Record Tuna Salad successfully imported.\n" +
            "Record Just Lettuce successfully imported.\n" +
            "Record Minion successfully imported.\n" +
            "Record Batman successfully imported.\n" +
            "Record Mario successfully imported.\n" +
            "Record Luigi successfully imported.\n" +
            "Record Bowser successfully imported.";

    @Autowired
    private ItemsController itemsController;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private FileIO fileParser;

    @Test
    public void allItemsImportedOutput() throws IOException {
        this.itemsRepository.deleteAll();
        this.itemsRepository.flush();
        String output = this.itemsController
                .importDataFromJSON(this.fileParser.read(Config.ITEMS_IMPORT_JSON)).trim();
        output = output.replaceAll("\\s+", "#");
        Assert.assertEquals("Insertion output is not the same!",
                output, EXPECTED_INSERTION_STRING.replaceAll("\\s+", "#"));
    }
}
