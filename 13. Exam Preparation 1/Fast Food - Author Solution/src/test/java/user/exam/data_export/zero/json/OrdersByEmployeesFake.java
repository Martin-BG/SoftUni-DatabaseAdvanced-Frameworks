package user.exam.data_export.zero.json;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class OrdersByEmployeesFake {
    private final static String EXPECTED_OUTPUT = "{\n" +
            "  \"employeeName\": \"Ariane Sloan\",\n" +
            "  \"orders\": [\n" +
            "    {\n" +
            "      \"customer\": \"Daniel\",\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"name\": \"Piron\",\n" +
            "          \"price\": 5.00,\n" +
            "          \"quantity\": 6\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Otvertka\",\n" +
            "          \"price\": 1.00,\n" +
            "          \"quantity\": 2\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Marulq\",\n" +
            "          \"price\": 1.00,\n" +
            "          \"quantity\": 2\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"customer\": \"Daniel\",\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"name\": \"Piron\",\n" +
            "          \"price\": 5.00,\n" +
            "          \"quantity\": 5\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Otvertka\",\n" +
            "          \"price\": 1.00,\n" +
            "          \"quantity\": 2\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Marulq\",\n" +
            "          \"price\": 1.00,\n" +
            "          \"quantity\": 2\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    private static final String FAKE_ORDERS = "<orders>\n" +
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
            "        <name>Otvertka</name>\n" +
            "        <quantity>2</quantity>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "        <name>Marulq</name>\n" +
            "        <quantity>2</quantity>\n" +
            "      </item>\n" +
            "    </items>\n" +
            "  </order>\n" +
            "  <order>\n" +
            "    <customer>Daniel</customer>\n" +
            "    <employee>Ariane Sloan</employee>\n" +
            "    <date>24/05/2017 21:58</date>\n" +
            "    <type>ForHere</type>\n" +
            "    <items>\n" +
            "      <item>\n" +
            "        <name>Piron</name>\n" +
            "        <quantity>6</quantity>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "        <name>Otvertka</name>\n" +
            "        <quantity>2</quantity>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "        <name>Marulq</name>\n" +
            "        <quantity>2</quantity>\n" +
            "      </item>\n" +
            "    </items>\n" +
            "  </order>\n" +
            "  <order>\n" +
            "    <customer>Bobbie</customer>\n" +
            "    <employee>Ariane Sloan</employee>\n" +
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
            "      <item>\n" +
            "        <name>Marulq</name>\n" +
            "        <quantity>2</quantity>\n" +
            "      </item>\n" +
            "    </items>\n" +
            "  </order>\n" +
            "</orders>";
    private static final String FAKE_ITEMS = "[\n" +
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
    public void testExportByEmployeeAndOrderType() throws IOException, JAXBException {
        this.orderRepository.deleteAll();
        this.orderRepository.flush();

        this.itemsRepository.deleteAll();
        this.itemsRepository.flush();

        this.itemsController.importDataFromJSON(FAKE_ITEMS);
        this.itemsRepository.flush();
        this.ordersController.importDataFromXML(FAKE_ORDERS);
        this.orderRepository.flush();
        String output = this.ordersController.exportOrdersByEmployeeAndOrderType("Ariane Sloan", "ForHere");
        output = output.replaceAll("\\s+", "");
        Assert.assertEquals(output, EXPECTED_OUTPUT.replaceAll("\\s+", ""));
    }
}

