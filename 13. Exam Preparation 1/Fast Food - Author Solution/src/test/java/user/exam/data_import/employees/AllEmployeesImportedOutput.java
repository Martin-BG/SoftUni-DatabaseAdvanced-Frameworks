package user.exam.data_import.employees;

import app.exam.ExamApplication;
import app.exam.config.Config;
import app.exam.controller.EmployeesController;
import app.exam.io.interfaces.FileIO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class AllEmployeesImportedOutput {
    private static final String EXPECTED_INSERTION_STRING = "Error: Invalid data.\n" +
            "Error: Invalid data.\n" +
            "Error: Invalid data.\n" +
            "Error: Invalid data.\n" +
            "Error: Invalid data.\n" +
            "Record Magda Bjork successfully imported.\n" +
            "Record Erich Hennigan successfully imported.\n" +
            "Record Shin Vallejos successfully imported.\n" +
            "Record Avery Rush successfully imported.\n" +
            "Record Coral Points successfully imported.\n" +
            "Record Xiao Raley successfully imported.\n" +
            "Record Kym Douse successfully imported.\n" +
            "Record Kendra Stangl successfully imported.\n" +
            "Record Lura Yeldell successfully imported.\n" +
            "Record Mohammad Norton successfully imported.\n" +
            "Record Tamika Thornsberry successfully imported.\n" +
            "Record Nancie Mcquarrie successfully imported.\n" +
            "Record Maxwell Shanahan successfully imported.\n" +
            "Record Classie Mettler successfully imported.\n" +
            "Record Denita Providence successfully imported.\n" +
            "Record Jerica Rupe successfully imported.\n" +
            "Record Nolan Jablonski successfully imported.\n" +
            "Record Mikki Vasques successfully imported.\n" +
            "Record Ariane Sloan successfully imported.\n" +
            "Record Herta Balser successfully imported.\n" +
            "Record Jacqualine Clune successfully imported.\n" +
            "Record Mervin Langone successfully imported.\n" +
            "Record Dorethea Mumford successfully imported.\n" +
            "Record Connie Barbosa successfully imported.\n" +
            "Record Katie Nilsen successfully imported.\n" +
            "Record Camille Peller successfully imported.\n" +
            "Record Annett Lewallen successfully imported.\n" +
            "Record Iris Foushee successfully imported.\n" +
            "Record Eric Toole successfully imported.\n" +
            "Record Justin Brazil successfully imported.\n" +
            "Record Felisa Frier successfully imported.\n" +
            "Record Lakiesha Huffines successfully imported.\n" +
            "Record Emory Lemos successfully imported.\n" +
            "Record Lanita Palmore successfully imported.\n" +
            "Record Janiece Owens successfully imported.\n" +
            "Record Maryland Palm successfully imported.\n" +
            "Record Sunday Eastep successfully imported.\n" +
            "Record Lucius Rotz successfully imported.\n" +
            "Record Stanton Dahl successfully imported.\n" +
            "Record Carmon Sesco successfully imported.\n" +
            "Record Willette Ugalde successfully imported.\n" +
            "Record Rose Blizzard successfully imported.\n" +
            "Record Caridad Cuenca successfully imported.\n" +
            "Record Tran Bullion successfully imported.\n" +
            "Record Fred Higby successfully imported.\n" +
            "Record Elizabet Trentham successfully imported.\n" +
            "Record Shirleen Vonruden successfully imported.\n" +
            "Record Oscar Dolan successfully imported.\n" +
            "Record Prince Betton successfully imported.\n" +
            "Record Jolanda Discher successfully imported.";

    @Autowired
    private EmployeesController employeesController;

    @Autowired
    private FileIO fileParser;

    @Test
    public void allPersonsImportedOutput() throws IOException {
        String output = this.employeesController.importDataFromJSON(this.fileParser.read(Config.EMPLOYEES_IMPORT_JSON)).trim();
        output = output.replaceAll("\\s+", "#");
        Assert.assertEquals("Insertion output is not the same!",
                output, EXPECTED_INSERTION_STRING.replaceAll("\\s+", "#"));
    }
}
