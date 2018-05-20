package app.exam.controller;

import app.exam.domain.dto.xml.CategoriesFrequentItemsXMLExportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    @Qualifier(value = "XMLParser")
    private Parser xmlParser;

    public String getCategoriesWithMostPopularItemsSorted(List<String> categoryNames) throws IOException, JAXBException {
        CategoriesFrequentItemsXMLExportDTO dto = this.categoryService.getCategoriesWithMostPopularItems(categoryNames);
        return this.xmlParser.write(dto);
//        return null;
    }
}
