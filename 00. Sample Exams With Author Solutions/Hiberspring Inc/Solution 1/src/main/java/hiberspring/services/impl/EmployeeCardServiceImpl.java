package hiberspring.services.impl;

import hiberspring.dtos.imports.EmployeeCardsImportFromJsonDto;
import hiberspring.dtos.views.EmployeeCardUnusedViewDto;
import hiberspring.entities.EmployeeCard;
import hiberspring.repositories.EmployeeCardRepository;
import hiberspring.services.EmployeeCardService;
import hiberspring.utilities.MapperConverter;
import hiberspring.validators.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {

    private final EmployeeCardRepository employeeCardRepository;
    private final MapperConverter mapperConverter;

    @Autowired
    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, MapperConverter mapperConverter) {
        this.employeeCardRepository = employeeCardRepository;
        this.mapperConverter = mapperConverter;
    }

    @Override
    public EmployeeCard createOne(EmployeeCardsImportFromJsonDto employeeCardsImportFromJsonDto) {
        EmployeeCard employeeCard = this.mapperConverter.convert(employeeCardsImportFromJsonDto, EmployeeCard.class);
        if (EntityValidator.isValid(employeeCard)) {
            if (isUniqueCardNumber(employeeCard.getNumber())) {
                employeeCard = this.employeeCardRepository.save(employeeCard);
                return employeeCard;
            }
        }
        return null;
    }

    @Override
    public List<EmployeeCardUnusedViewDto> getUnusedCards() {
        List<EmployeeCard> unusedCards = this.employeeCardRepository.getUnusedCards();
        EmployeeCardUnusedViewDto[] unusedViewDtos = this.mapperConverter.convert(unusedCards, EmployeeCardUnusedViewDto[].class);
        return Arrays.asList(unusedViewDtos);
    }

    private boolean isUniqueCardNumber(String cardNumber) {
        EmployeeCard employeeCardByNumber = this.employeeCardRepository.getEmployeeCardByNumber(cardNumber);
        return employeeCardByNumber == null;
    }
}
