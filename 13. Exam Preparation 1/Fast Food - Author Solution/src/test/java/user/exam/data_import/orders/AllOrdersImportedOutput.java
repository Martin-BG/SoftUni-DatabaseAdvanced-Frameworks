package user.exam.data_import.orders;

import app.exam.ExamApplication;
import app.exam.config.Config;
import app.exam.controller.OrdersController;
import app.exam.io.interfaces.FileIO;
import app.exam.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class AllOrdersImportedOutput {
    private static final String EXPECTED_INSERTION_STRING = "Order for Garry on 21/08/2017 13:22 added.\n" +
            "Order for Pablo on 23/11/2017 16:25 added.\n" +
            "Order for Enrique on 11/06/2017 14:08 added.\n" +
            "Order for Joann on 08/09/2017 23:19 added.\n" +
            "Order for Ray on 25/08/2017 20:13 added.\n" +
            "Order for Roberta on 02/03/2017 07:19 added.\n" +
            "Order for Daniel on 16/12/2017 20:13 added.\n" +
            "Order for Yolanda on 28/10/2017 04:25 added.\n" +
            "Order for Pablo on 26/03/2017 06:33 added.\n" +
            "Order for Ray on 11/03/2017 04:07 added.\n" +
            "Order for Bobbie on 09/02/2017 01:50 added.\n" +
            "Order for Miguel on 03/03/2017 21:41 added.\n" +
            "Order for Roberta on 26/09/2017 22:15 added.\n" +
            "Order for Leona on 06/02/2017 00:04 added.\n" +
            "Order for Stacey on 07/10/2017 06:00 added.\n" +
            "Order for Enrique on 19/07/2017 17:58 added.\n" +
            "Order for Guillermo on 23/08/2017 08:24 added.\n" +
            "Order for Darryl on 25/01/2017 13:02 added.\n" +
            "Order for Daniel on 24/05/2017 21:58 added.\n" +
            "Order for Bobbie on 08/04/2017 19:53 added.";

    @Autowired
    private OrdersController ordersController;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FileIO fileParser;

    @Test
    public void allOrdersImportedOutput() throws IOException {
        this.orderRepository.deleteAll();
        this.orderRepository.flush();
        //entity manager create native query items and employees
        String output = this.ordersController
                .importDataFromXML(this.fileParser.read(Config.ORDERS_IMPORT_XML)).trim();
        output = output.replaceAll("\\s+", "#");
        Assert.assertEquals("Insertion output is not the same!",
                output, EXPECTED_INSERTION_STRING.replaceAll("\\s+", "#"));
    }
}
