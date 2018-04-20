package user.exam.data_import.employees;

import app.exam.ExamApplication;
import app.exam.controller.EmployeesController;
import app.exam.io.interfaces.FileIO;
import app.exam.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class AllEmployeesImportedCount {

    private static final int EXPECTED_EMPLOYEES_COUNT = 50;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeesController employeesController;
    @Autowired
    private FileIO fileParser;

    @Test
    public void testEmployeesImportedCount() throws Exception {
        long count = this.employeeRepository
                .findAll()
                .spliterator()
                .getExactSizeIfKnown();
        Assert.assertEquals(EXPECTED_EMPLOYEES_COUNT, count);
    }
}
