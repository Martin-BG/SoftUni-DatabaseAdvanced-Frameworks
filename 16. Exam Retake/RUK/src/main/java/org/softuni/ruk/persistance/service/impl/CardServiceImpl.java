package org.softuni.ruk.persistance.service.impl;

import org.softuni.ruk.config.Config;
import org.softuni.ruk.model.dto.binding.xml.CardFromXmlDto;
import org.softuni.ruk.model.entity.BankAccount;
import org.softuni.ruk.model.entity.Card;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.persistance.repository.CardRepository;
import org.softuni.ruk.persistance.service.api.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    private final CardRepository repository;
    private final ModelParser mapper;
    private final ValidationUtil validator;
    private final BankAccountServiceImpl bankAccountService;

    @Autowired
    public CardServiceImpl(final CardRepository repository,
                           final ModelParser mapper,
                           final ValidationUtil validator,
                           final BankAccountServiceImpl bankAccountService) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
        this.bankAccountService = bankAccountService;
    }

    @Override
    public <T> String create(final T t) {
        CardFromXmlDto dto = (CardFromXmlDto) t;

        if (!this.validator.isValid(dto)) {
            return Config.ERROR_INCORRECT_DATA;
        }

        BankAccount bankAccount = this.bankAccountService.findOneByAccountNumber(dto.getAccountNumber());

        if (bankAccount == null) {
            return Config.ERROR_INCORRECT_DATA;
        }

        Card card = this.mapper.convert(dto, Card.class);
        card.setBankAccount(bankAccount);

        this.repository.saveAndFlush(card);

        bankAccount.getCards().add(card);

        return String.format(Config.SUCCESSFULLY_IMPORTED, "Card", card.getCardNumber());

    }
}
