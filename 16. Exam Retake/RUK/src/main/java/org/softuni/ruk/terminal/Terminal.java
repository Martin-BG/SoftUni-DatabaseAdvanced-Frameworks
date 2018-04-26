package org.softuni.ruk.terminal;

import org.softuni.ruk.config.Config;
import org.softuni.ruk.conroller.*;
import org.softuni.ruk.io.interfaces.ConsoleIO;
import org.softuni.ruk.io.interfaces.FileIO;
import org.softuni.ruk.model.dto.binding.json.BranchFromJsonDto;
import org.softuni.ruk.model.dto.binding.json.ClientFromJsonDto;
import org.softuni.ruk.model.dto.binding.json.EmployeeFromJsonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final BranchController branchController;
    private final BankAccountController bankAccountController;
    private final CardController cardController;
    private final ClientController clientController;
    private final EmployeeController employeeController;

    @Autowired
    public Terminal(final FileIO fileIO,
                    final ConsoleIO consoleIO,
                    final BranchController branchController,
                    final BankAccountController bankAccountController,
                    final CardController cardController,
                    final ClientController clientController,
                    final EmployeeController employeeController) {
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.branchController = branchController;
        this.bankAccountController = bankAccountController;
        this.cardController = cardController;
        this.clientController = clientController;
        this.employeeController = employeeController;
    }

    @Override
    public void run(String... args) {

        this.consoleIO.write(
                this.branchController.importFromJSON(
                        fileIO.read(Config.BRANCHES_IMPORT_JSON), BranchFromJsonDto[].class));

        this.consoleIO.write(
                this.employeeController.importFromJSON(
                        fileIO.read(Config.EMPLOYEES_IMPORT_JSON), EmployeeFromJsonDto[].class));

        this.consoleIO.write(
                this.clientController.importFromJSON(
                        fileIO.read(Config.CLIENTS_IMPORT_JSON), ClientFromJsonDto[].class));

        this.consoleIO.write(
                this.bankAccountController.importFromXML(
                        fileIO.read(Config.BANK_ACCOUNTS_IMPORT_XML)));

        this.consoleIO.write(
                this.cardController.importFromXML(
                        fileIO.read(Config.CARDS_IMPORT_XML)));

        this.fileIO.write(
                this.employeeController.exportAllByClients(),
                Config.TOP_EMPLOYEES_EXPORT_JSON);

        this.fileIO.write(
                this.clientController.exportClientWithMostCards(),
                Config.FAMILY_GUY_EXPORT_XML);
    }
}
