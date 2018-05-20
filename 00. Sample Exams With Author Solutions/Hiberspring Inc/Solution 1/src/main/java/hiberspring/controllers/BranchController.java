package hiberspring.controllers;

import hiberspring.constants.MessageConstants;
import hiberspring.dtos.imports.BranchImportFromJsonDto;
import hiberspring.dtos.views.branch.BranchesViewDto;
import hiberspring.entities.Branch;
import hiberspring.io.Writer;
import hiberspring.serializers.Serializer;
import hiberspring.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class BranchController {
    private static final String BRANCHES_JSON_INPUT_PATH = "/json/input/branches.json";
    private static final String TOP_BRANCHES_XML_OUTPUT_PATH = "src/main/resources/xml/output/top-branches.xml";

    private final Serializer jsonSerializer;
    private final Serializer xmlSerializer;
    private final BranchService branchService;
    private final Writer writer;

    @Autowired
    public BranchController(@Qualifier("json") Serializer jsonSerializer, @Qualifier("xml") Serializer xmlSerializer, BranchService branchService, Writer writer) {
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
        this.branchService = branchService;
        this.writer = writer;
    }

    public void importBranches() {
        BranchImportFromJsonDto[] branchImportFromJsonDtos = this.jsonSerializer.deserialize(BranchImportFromJsonDto[].class, BRANCHES_JSON_INPUT_PATH);
        for (BranchImportFromJsonDto branchImportFromJsonDto : branchImportFromJsonDtos) {
            Branch branch = this.branchService.createOne(branchImportFromJsonDto);
            if (branch != null) {
                this.writer.println(MessageConstants.SUCCESSFULLY_IMPORTED_ENTITY_MESSAGE, branch.getClass().getSimpleName(), branch.getName());
            } else {
                this.writer.println(MessageConstants.INVALID_INPUT_DATA_MESSAGE);
            }
        }
    }

    public void exportBranches() {
        BranchesViewDto branchesViewDto = this.branchService.getTopBranches();
        this.xmlSerializer.serialize(branchesViewDto, TOP_BRANCHES_XML_OUTPUT_PATH);
    }
}
