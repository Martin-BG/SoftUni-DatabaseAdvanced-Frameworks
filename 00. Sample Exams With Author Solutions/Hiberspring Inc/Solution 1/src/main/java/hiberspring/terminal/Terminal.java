package hiberspring.terminal;

import hiberspring.controllers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    private final TownController townController;
    private final BranchController branchController;
    private final EmployeeCardController employeeCardController;
    private final ProductController productController;
    private final EmployeeController employeeController;

    @Autowired
    public Terminal(TownController townController, BranchController branchController, EmployeeCardController employeeCardController, ProductController productController, EmployeeController employeeController) {
        this.townController = townController;
        this.branchController = branchController;
        this.employeeCardController = employeeCardController;
        this.productController = productController;
        this.employeeController = employeeController;
    }

    @Override
    public void run(String... strings) throws Exception {

        //import Json
        this.townController.importTowns();
        this.branchController.importBranches();
        this.employeeCardController.importEmployeeCards();

        //imports Xml
        this.productController.importProducts();
        this.employeeController.importEmployees();

        //export Json
        this.employeeCardController.exportUnusedCards();
        this.employeeController.exportProductiveEmployees();

        //export Xml
        this.townController.exportTowns();
        this.branchController.exportBranches();
    }
}
