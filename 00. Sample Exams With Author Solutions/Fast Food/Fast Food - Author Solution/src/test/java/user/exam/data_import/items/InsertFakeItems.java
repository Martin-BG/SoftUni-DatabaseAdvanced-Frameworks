package user.exam.data_import.items;

import app.exam.ExamApplication;
import app.exam.controller.ItemsController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class InsertFakeItems {
    private final static String FAKE_INFO = "[\n" +
            "  {\n" +
            "    \"name\": \"H\",\n" +
            "    \"price\": 0.000001,\n" +
            "    \"category\": \"Invalid\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Ha\",\n" +
            "    \"price\": 5.00,\n" +
            "    \"category\": \"Invalid\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"xXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\",\n" +
            "    \"price\": 1.00,\n" +
            "    \"category\": \"Invalid\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Invaliddddddddddddddddddddddddd\",\n" +
            "    \"price\": -200,\n" +
            "    \"category\": \"Invalid\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Valid\",\n" +
            "    \"price\": 20,\n" +
            "    \"category\": \"Invalid\"\n" +
            "  }\n" +
            "]";
    private final static String EXPECTED_INSERTION_STRING = "Error: Invalid data.\n" +
            "Error: Invalid data.\n" +
            "Error: Invalid data.\n" +
            "Error: Invalid data.\n" +
            "Record Valid successfully imported.";
    @Autowired
    private ItemsController itemsController;

    @Test
    public void fakeItemImport() throws IOException {
        String output = this.itemsController.importDataFromJSON(FAKE_INFO).trim();
        output = output.replaceAll("\\s+", "");
        Assert.assertEquals("Insertion output is not the same!",
                output, EXPECTED_INSERTION_STRING.replaceAll("\\s+", ""));
    }
}
