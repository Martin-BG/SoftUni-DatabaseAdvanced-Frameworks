package user.exam.data_export.zero;

import app.exam.ExamApplication;
import app.exam.controller.CategoryController;
import app.exam.controller.ItemsController;
import app.exam.controller.OrdersController;
import app.exam.repository.ItemsRepository;
import app.exam.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class MostPopularItemFakesSecond {
    private final static String FAKE_ITEMS = "[\n" +
            "  {\n" +
            "    \"name\": \"Piron\",\n" +
            "    \"price\": 5.00,\n" +
            "    \"category\": \"Instrumenti\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Otvertka\",\n" +
            "    \"price\": 1.00,\n" +
            "    \"category\": \"Instrumenti\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Marulq\",\n" +
            "    \"price\": 1.00,\n" +
            "    \"category\": \"Zelenchuci\"\n" +
            "  }\n" +
            "]";
    private final static String FAKE_ORDERS = "<orders>\n" +
            "  <order>\n" +
            "    <customer>Daniel</customer>\n" +
            "    <employee>Ariane Sloan</employee>\n" +
            "    <date>24/05/2017 21:58</date>\n" +
            "    <type>ForHere</type>\n" +
            "    <items>\n" +
            "      <item>\n" +
            "        <name>Piron</name>\n" +
            "        <quantity>5</quantity>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "        <name>Marulq</name>\n" +
            "        <quantity>2</quantity>\n" +
            "      </item>\n" +
            "    </items>\n" +
            "  </order>\n" +
            "  <order>\n" +
            "    <customer>Bobbie</customer>\n" +
            "    <employee>Avery Rush</employee>\n" +
            "    <date>08/04/2017 19:53</date>\n" +
            "    <type>ToGo</type>\n" +
            "    <items>\n" +
            "      <item>\n" +
            "        <name>Piron</name>\n" +
            "        <quantity>2</quantity>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "        <name>Otvertka</name>\n" +
            "        <quantity>2</quantity>\n" +
            "      </item>\n" +
            "    </items>\n" +
            "  </order>\n" +
            "</orders>";
    private final static String EXPECTED_OUTPUT = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<categories>\n" +
            "    <category>\n" +
            "        <name>Instrumenti</name>\n" +
            "        <most-popular-item>\n" +
            "            <name>Piron</name>\n" +
            "            <total-made>35.0</total-made>\n" +
            "            <times-sold>7</times-sold>\n" +
            "        </most-popular-item>\n" +
            "    </category>\n" +
            "    <category>\n" +
            "        <name>Zelenchuci</name>\n" +
            "        <most-popular-item>\n" +
            "            <name>Marulq</name>\n" +
            "            <total-made>2.0</total-made>\n" +
            "            <times-sold>2</times-sold>\n" +
            "        </most-popular-item>\n" +
            "    </category>\n" +
            "</categories>\n";
    @Autowired
    private CategoryController categoryController;
    @Autowired
    private OrdersController ordersController;
    @Autowired
    private ItemsController itemsController;
    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testMostPopularItemFake() throws IOException, JAXBException {
        this.orderRepository.deleteAll();
        this.orderRepository.flush();

        this.itemsRepository.deleteAll();
        this.itemsRepository.flush();

        this.itemsController.importDataFromJSON(FAKE_ITEMS);
        this.itemsRepository.flush();
        this.ordersController.importDataFromXML(FAKE_ORDERS);
        this.orderRepository.flush();

        List<String> categoryNames = new ArrayList<>();
        categoryNames.add("Instrumenti");
        categoryNames.add("Zelenchuci");
        String output = this.categoryController.getCategoriesWithMostPopularItemsSorted(categoryNames);
        output = output.replaceAll("\\s+", "");
        Assert.assertEquals(output, EXPECTED_OUTPUT.replaceAll("\\s+", ""));
    }
}
