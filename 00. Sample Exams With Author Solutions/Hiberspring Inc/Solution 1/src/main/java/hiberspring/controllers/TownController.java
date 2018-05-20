package hiberspring.controllers;

import hiberspring.constants.MessageConstants;
import hiberspring.dtos.imports.TownImportFromJsonDto;
import hiberspring.dtos.views.town.TownsViewDto;
import hiberspring.entities.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import hiberspring.io.Writer;
import hiberspring.serializers.Serializer;
import hiberspring.services.TownService;

@Controller
public class TownController {
    private static final String TOWNS_JSON_INPUT_PATH = "/json/input/towns.json";
    private static final String TOWNS_XML_OUTPUT_PATH = "src/main/resources/xml/output/towns.xml";

    private final Serializer jsonSerializer;
    private final Serializer xmlSerializer;
    private final TownService townService;
    private final Writer writer;

    @Autowired
    public TownController(@Qualifier("json") Serializer jsonSerializer, @Qualifier("xml") Serializer xmlSerializer, TownService townService, Writer writer) {
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
        this.townService = townService;
        this.writer = writer;
    }

    public void importTowns() {
        TownImportFromJsonDto[] townImportFromJsonDtos = this.jsonSerializer.deserialize(TownImportFromJsonDto[].class, TOWNS_JSON_INPUT_PATH);
        for (TownImportFromJsonDto townImportFromJsonDto : townImportFromJsonDtos) {
            Town town = this.townService.createOne(townImportFromJsonDto);
            if (town != null) {
                this.writer.println(MessageConstants.SUCCESSFULLY_IMPORTED_ENTITY_MESSAGE, town.getClass().getSimpleName(), town.getName());
            } else {
               this.writer.println(MessageConstants.INVALID_INPUT_DATA_MESSAGE);
            }
        }
    }

    public void exportTowns() {
        TownsViewDto towns = this.townService.getTowns();
        this.xmlSerializer.serialize(towns, TOWNS_XML_OUTPUT_PATH);
    }
}
