package org.softuni.ruk.persistance.service.impl;

import org.softuni.ruk.config.Config;
import org.softuni.ruk.model.dto.binding.xml.BankAccountFromXmlDto;
import org.softuni.ruk.model.entity.BankAccount;
import org.softuni.ruk.model.entity.Client;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.persistance.repository.BankAccountRepository;
import org.softuni.ruk.persistance.service.api.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repository;
    private final ModelParser mapper;
    private final ValidationUtil validator;
    private final ClientServiceImpl clientService;

    @Autowired
    public BankAccountServiceImpl(final BankAccountRepository repository,
                                  final ModelParser mapper,
                                  final ValidationUtil validator,
                                  final ClientServiceImpl clientService) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
        this.clientService = clientService;
    }

    @Override
    public <T> String create(final T t) {
        BankAccountFromXmlDto dto = (BankAccountFromXmlDto) t;

        if (!this.validator.isValid(dto)) {
            return Config.ERROR_INCORRECT_DATA;
        }

        List<Client> client = this.clientService.findOneByFullName(dto.getClientFullName());

        if (client.isEmpty()) {
            return Config.ERROR_INCORRECT_DATA;
        }

        BankAccount bankAccount = this.mapper.convert(dto, BankAccount.class);
        bankAccount.setClient(client.get(0));

        this.repository.saveAndFlush(bankAccount);

        client.forEach(c -> c.setBankAccount(bankAccount));

        return String.format(Config.SUCCESSFULLY_IMPORTED, "BankAccount", bankAccount.getAccountNumber());
    }

    BankAccount findOneByAccountNumber(final String accountNumber) {
        return this.repository.findOneByAccountNumber(accountNumber);
    }
}
