package user.exam.data_import.employees;

import app.exam.ExamApplication;
import app.exam.controller.EmployeesController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class InsertFakeEmployee {
    private final static String FAKE_INFO = "[\n" +
            "  {\n" +
            "    \"name\": \"N\",\n" +
            "    \"age\": 100,\n" +
            "    \"position\": \"Invalid\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Too Young\",\n" +
            "    \"age\": -5,\n" +
            "    \"position\": \"Invalid\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Simona\",\n" +
            "    \"age\": 79,\n" +
            "    \"position\": \"Invalid\"\n" +
            "  }\n" +
            "]";
    private final static String EXPECTED_INSERTION_STRING = "Error: Invalid data.\n" +
            "Error: Invalid data.\n" +
            "Record Simona successfully imported.";
    @Autowired
    private EmployeesController employeesController;

    @Test
    public void allPersonsImportedOutput() throws IOException {
        String output = this.employeesController.importDataFromJSON(FAKE_INFO).trim();
        output = output.replaceAll("\\s+", "");
        Assert.assertEquals("Insertion output is not the same!",
                output, EXPECTED_INSERTION_STRING.replaceAll("\\s+", ""));
    }
}
