package user.exam.data_export.zero.json.a;

import app.exam.ExamApplication;
import app.exam.controller.OrdersController;
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
public class ЕxportOrdersByEmployeeAndOrderType {
    private final static String EXPECTED_OUTPUT = "{\n" +
            "  \"employeeName\": \"Avery Rush\",\n" +
            "  \"orders\": [\n" +
            "    {\n" +
            "      \"customer\": \"Stacey\",\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"name\": \"Cheeseburger\",\n" +
            "          \"price\": 6.00,\n" +
            "          \"quantity\": 5\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Double Cheeseburger\",\n" +
            "          \"price\": 6.50,\n" +
            "          \"quantity\": 3\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Bacon Deluxe\",\n" +
            "          \"price\": 9.00,\n" +
            "          \"quantity\": 1\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Luigi\",\n" +
            "          \"price\": 2.10,\n" +
            "          \"quantity\": 5\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"customer\": \"Pablo\",\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"name\": \"Double Cheeseburger\",\n" +
            "          \"price\": 6.50,\n" +
            "          \"quantity\": 3\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Bacon Deluxe\",\n" +
            "          \"price\": 9.00,\n" +
            "          \"quantity\": 5\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"customer\": \"Bobbie\",\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"name\": \"Fries\",\n" +
            "          \"price\": 1.50,\n" +
            "          \"quantity\": 2\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Crispy Fries\",\n" +
            "          \"price\": 2.00,\n" +
            "          \"quantity\": 5\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Tuna Salad\",\n" +
            "          \"price\": 3.00,\n" +
            "          \"quantity\": 2\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"customer\": \"Joann\",\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"name\": \"Bacon Deluxe\",\n" +
            "          \"price\": 9.00,\n" +
            "          \"quantity\": 1\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Minion\",\n" +
            "          \"price\": 2.20,\n" +
            "          \"quantity\": 2\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    @Autowired
    private OrdersController ordersController;

    @Test
    public void testExportByEmployeeAndOrderType() throws IOException, JAXBException {
        String output = this.ordersController.exportOrdersByEmployeeAndOrderType("Avery Rush", "ToGo");
        output = output.replaceAll("\\s+", "");
        Assert.assertTrue(EXPECTED_OUTPUT.replaceAll("\\s+", "").equals(output));
    }
}
