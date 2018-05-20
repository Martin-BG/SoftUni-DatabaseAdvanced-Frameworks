package hiberspring.controllers;

import hiberspring.constants.MessageConstants;
import hiberspring.dtos.imports.EmployeeCardsImportFromJsonDto;
import hiberspring.dtos.views.EmployeeCardUnusedViewDto;
import hiberspring.entities.EmployeeCard;
import hiberspring.io.Writer;
import hiberspring.serializers.Serializer;
import hiberspring.services.EmployeeCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EmployeeCardController {
    private static final String EMPLOYEE_CARDS_JSON_INPUT_PATH = "/json/input/employee_cards.json";
    private static final String EMPLOYEE_CARDS_UNUSED_JSON_OUTPUT_PATH = "/src/main/resources/json/output/free_cards.json";

    private final Serializer jsonSerializer;
    private final EmployeeCardService employeeCardService;
    private final Writer writer;

    @Autowired
    public EmployeeCardController(@Qualifier("json") Serializer jsonSerializer, EmployeeCardService employeeCardService, Writer writer) {
        this.jsonSerializer = jsonSerializer;
        this.employeeCardService = employeeCardService;
        this.writer = writer;
    }

    public void importEmployeeCards() {
        EmployeeCardsImportFromJsonDto[] employeeCardsImportFromJsonDtos = this.jsonSerializer.deserialize(EmployeeCardsImportFromJsonDto[].class, EMPLOYEE_CARDS_JSON_INPUT_PATH);
        for (EmployeeCardsImportFromJsonDto employeeCardsImportFromJsonDto : employeeCardsImportFromJsonDtos) {
            EmployeeCard employeeCard = this.employeeCardService.createOne(employeeCardsImportFromJsonDto);
            if (employeeCard != null) {
                this.writer.println(MessageConstants.SUCCESSFULLY_IMPORTED_ENTITY_MESSAGE, employeeCard.getClass().getSimpleName(), employeeCard.getNumber());
            } else {
                this.writer.println(MessageConstants.INVALID_INPUT_DATA_MESSAGE);
            }
        }
    }

    public void exportUnusedCards() {
        List<EmployeeCardUnusedViewDto> employeeCardUnusedViewDtos = this.employeeCardService.getUnusedCards();
        this.jsonSerializer.serialize(employeeCardUnusedViewDtos, EMPLOYEE_CARDS_UNUSED_JSON_OUTPUT_PATH);
    }
}
