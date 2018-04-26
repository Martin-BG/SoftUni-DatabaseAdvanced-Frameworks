package org.softuni.ruk.persistance.service.impl;

import org.softuni.ruk.config.Config;
import org.softuni.ruk.model.dto.binding.json.ClientFromJsonDto;
import org.softuni.ruk.model.dto.view.xml.BankAccountWithCardsDto;
import org.softuni.ruk.model.dto.view.xml.CardDto;
import org.softuni.ruk.model.dto.view.xml.FamilyGuyWithCardsWrapperDto;
import org.softuni.ruk.model.entity.Client;
import org.softuni.ruk.model.entity.Employee;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.persistance.repository.ClientRepository;
import org.softuni.ruk.persistance.service.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final ModelParser mapper;
    private final ValidationUtil validator;
    private final EmployeeServiceImpl employeeService;

    @Autowired
    public ClientServiceImpl(final ClientRepository repository,
                             final ModelParser mapper,
                             final ValidationUtil validator,
                             final EmployeeServiceImpl employeeService) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
        this.employeeService = employeeService;
    }

    @Override
    public <T> String create(final T t) {
        ClientFromJsonDto dto = (ClientFromJsonDto) t;

        if (!this.validator.isValid(dto)) {
            return Config.ERROR_INCORRECT_DATA;
        }

        String[] employeeNames = dto.getAppointedEmployee().split("\\s+");
        if (employeeNames.length != 2) {
            return Config.ERROR_INCORRECT_DATA;
        }

        Employee employee = this.employeeService.findOneByFirstNameAndLastName(employeeNames[0], employeeNames[1]);

        if (employee == null) {
            return Config.ERROR_INCORRECT_DATA;
        }

        Client client = new Client();
        client.setFullName(String.format("%s %s", dto.getFirstName(), dto.getLastName()));
        client.setAge(dto.getAge());

        this.repository.saveAndFlush(client);

        employee.getClients().add(client);

        return String.format(Config.SUCCESSFULLY_IMPORTED, "Client", client.getFullName());
    }

    List<Client> findOneByFullName(final String fullName) {
        return this.repository.findAllByFullNameEquals(fullName);
    }

    @Override
    public FamilyGuyWithCardsWrapperDto findClientWithMostCards() {

        final List<Client> clients = this.repository.findClientWithMostCards();
        if (clients.isEmpty()) {
            return null;
        }
        final Client client = clients.get(0);

        FamilyGuyWithCardsWrapperDto dtwWrapper = new FamilyGuyWithCardsWrapperDto();
        dtwWrapper.setAge(client.getAge());
        dtwWrapper.setFullName(client.getFullName());
        dtwWrapper.setBankAccountDto(new BankAccountWithCardsDto(
                client.getBankAccount()
                        .getCards()
                        .stream()
                        .map(card -> {
                            CardDto dto = new CardDto();
                            dto.setCardNumber(card.getCardNumber());
                            dto.setStatus(card.getCardStatus());
                            return dto;
                        }).collect(Collectors.toList()),
                client.getBankAccount().getAccountNumber()));

        System.out.println();
        return dtwWrapper;
    }
}
