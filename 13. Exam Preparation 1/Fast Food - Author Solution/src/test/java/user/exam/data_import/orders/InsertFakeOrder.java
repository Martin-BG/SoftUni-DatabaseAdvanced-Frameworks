package user.exam.data_import.orders;

import app.exam.ExamApplication;
import app.exam.controller.OrdersController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class InsertFakeOrder {
    private final static String FAKE_INFO = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<orders>\n" +
            "  <order>\n" +
            "    <customer>Garry</customer>\n" +
            "    <employee>Maxwell Shanahan</employee>\n" +
            "    <date>21/08/2017 14:22</date>\n" +
            "    <type>ForHere</type>\n" +
            "    <items>\n" +
            "      <item>\n" +
            "        <name>Quarter Pounder</name>\n" +
            "        <quantity>2</quantity>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "        <name>Premium chicken sandwich</name>\n" +
            "        <quantity>2</quantity>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "        <name>Chicken Tenders</name>\n" +
            "        <quantity>4</quantity>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "        <name>Just Lettuce</name>\n" +
            "        <quantity>4</quantity>\n" +
            "      </item>\n" +
            "    </items>\n" +
            "  </order>\n" +
            "  <order>\n" +
            "    <customer>Garry</customer>\n" +
            "    <employee>Maxwell Shanahannn</employee>\n" +
            "    <date>21/08/2017 13:22</date>\n" +
            "    <type>ForHere</type>\n" +
            "    <items>\n" +
            "      <item>\n" +
            "        <name>Quarter Pounder</name>\n" +
            "        <quantity>2</quantity>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "        <name>Premium chicken sandwich</name>\n" +
            "        <quantity>2</quantity>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "        <name>Chicken Tenders</name>\n" +
            "        <quantity>4</quantity>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "        <name>Just Lettuce</name>\n" +
            "        <quantity>4</quantity>\n" +
            "      </item>\n" +
            "    </items>\n" +
            "  </order>\n" +
            "  <order>\n" +
            "    <customer>Pablo</customer>\n" +
            "    <employee>Avery Rush</employee>\n" +
            "    <date>23/11/2017 16:25</date>\n" +
            "    <type>ToGoGo</type>\n" +
            "    <items>\n" +
            "      <item>\n" +
            "        <name>Double Cheeseburger</name>\n" +
            "        <quantity>3</quantity>\n" +
            "      </item>\n" +
            "      <item>\n" +
            "        <name>Bacon Deluxe</name>\n" +
            "        <quantity>5</quantity>\n" +
            "      </item>\n" +
            "    </items>\n" +
            "  </order>\n" +
            "</orders>";
    private final static String EXPECTED_INSERTION_STRING = "Order for Garry on 21/08/2017 14:22 added.\n" +
            "Error: Invalid data.\n" +
            "Error: Invalid data.\n";
    @Autowired
    private OrdersController ordersController;

    @Test
    public void fakeOrderImport() throws IOException {
        String output = this.ordersController.importDataFromXML(FAKE_INFO).trim();
        output = output.replaceAll("\\s+", "");
        Assert.assertEquals("Insertion output is not the same!",
                output, EXPECTED_INSERTION_STRING.replaceAll("\\s+", ""));
    }
}
