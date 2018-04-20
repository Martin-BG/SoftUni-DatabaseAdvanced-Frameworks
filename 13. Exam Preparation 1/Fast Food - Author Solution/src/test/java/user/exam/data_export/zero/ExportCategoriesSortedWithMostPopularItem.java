package user.exam.data_export.zero;

import app.exam.ExamApplication;
import app.exam.controller.CategoryController;
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
public class ExportCategoriesSortedWithMostPopularItem {
    private final static String EXPECTED_OUTPUT = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<categories>\n" +
            "    <category>\n" +
            "        <name>Chicken</name>\n" +
            "        <most-popular-item>\n" +
            "            <name>Chicken Tenders</name>\n" +
            "            <total-made>44.0</total-made>\n" +
            "            <times-sold>11</times-sold>\n" +
            "        </most-popular-item>\n" +
            "    </category>\n" +
            "    <category>\n" +
            "        <name>Toys</name>\n" +
            "        <most-popular-item>\n" +
            "            <name>Minion</name>\n" +
            "            <total-made>24.200000000000003</total-made>\n" +
            "            <times-sold>11</times-sold>\n" +
            "        </most-popular-item>\n" +
            "    </category>\n" +
            "    <category>\n" +
            "        <name>Drinks</name>\n" +
            "        <most-popular-item>\n" +
            "            <name>Purple Drink</name>\n" +
            "            <total-made>9.1</total-made>\n" +
            "            <times-sold>7</times-sold>\n" +
            "        </most-popular-item>\n" +
            "    </category>\n" +
            "</categories>";
    @Autowired
    private CategoryController categoryController;

    @Test
    public void testCategoriesSortedWithPopularItem() throws IOException, JAXBException {
        List<String> names = new ArrayList<>();
        names.add("Chicken");
        names.add("Toys");
        names.add("Drinks");
        String output = this.categoryController.getCategoriesWithMostPopularItemsSorted(names);
        output = output.replaceAll("\\s+", "");
        Assert.assertEquals(output, EXPECTED_OUTPUT.replaceAll("\\s+", ""));
    }
}
