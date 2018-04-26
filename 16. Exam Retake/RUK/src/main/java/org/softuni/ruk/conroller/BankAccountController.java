package org.softuni.ruk.conroller;

import org.softuni.ruk.model.dto.binding.xml.BankAccountFromXmlDto;
import org.softuni.ruk.model.dto.binding.xml.BankAccountsWrapperFromXmlDto;
import org.softuni.ruk.parser.DataImporter;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.persistance.service.api.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class BankAccountController {

    private final BankAccountService service;
    private final DataImporter importer;
    private final Parser parser;

    @Autowired
    public BankAccountController(final BankAccountService service,
                                 final DataImporter importer,
                                 @Qualifier("XMLParser") final Parser parser) {
        this.service = service;
        this.importer = importer;
        this.parser = parser;
    }

    public String importFromXML(String xmlContent) {
        BankAccountsWrapperFromXmlDto accounts = parser.read(BankAccountsWrapperFromXmlDto.class, xmlContent);
        return this.importer.importData(
                accounts.getAccounts().toArray(new BankAccountFromXmlDto[0]),
                this.service);
    }
}
