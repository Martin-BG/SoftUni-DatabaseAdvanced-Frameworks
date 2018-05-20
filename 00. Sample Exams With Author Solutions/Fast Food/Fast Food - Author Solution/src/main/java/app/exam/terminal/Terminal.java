package app.exam.terminal;

import app.exam.controller.CategoryController;
import app.exam.controller.EmployeesController;
import app.exam.controller.ItemsController;
import app.exam.controller.OrdersController;
import app.exam.io.interfaces.ConsoleIO;
import app.exam.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {
    @Autowired
    private EmployeesController employeesController;

    @Autowired
    private ItemsController itemsController;

    @Autowired
    private OrdersController ordersController;

    @Autowired
    private CategoryController categoryController;

    @Autowired
    private ConsoleIO consoleIO;

    @Autowired
    private FileIO fileIOUtil;


    @Override
    public void run(String... args) throws Exception {
        //import
//        this.consoleIO.write(this.employeesController.importDataFromJSON(this.fileIOUtil.read(Config.EMPLOYEES_IMPORT_JSON)));
//        this.consoleIO.write(this.itemsController.importDataFromJSON(this.fileIOUtil.read(Config.ITEMS_IMPORT_JSON)));
        //this.consoleIO.write(this.ordersController.importDataFromXML(this.fileIOUtil.read(Config.ORDERS_IMPORT_XML)));
        //export
//        this.consoleIO.write(this.ordersController
//                .exportOrdersByEmployeeAndOrderType("Avery Rush", "ToGo"));
//        List<String> names = new ArrayList<>();
//        names.add("Chicken");
//        names.add("Toys");
//        names.add("Drinks");
        //this.consoleIO.write(this.categoryController.getCategoriesWithMostPopularItemsSorted(names));
    }
}
