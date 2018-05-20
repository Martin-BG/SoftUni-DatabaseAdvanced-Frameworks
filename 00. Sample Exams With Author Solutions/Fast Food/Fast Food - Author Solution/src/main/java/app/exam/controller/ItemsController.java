package app.exam.controller;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    @Autowired
    @Qualifier(value = "JSONParser")
    private Parser jsonParser;

    public String importDataFromJSON(String jsonContent) {
        ItemJSONImportDTO[] itemJSONImportDTOS = null;
        try {
            itemJSONImportDTOS = this.jsonParser.read(ItemJSONImportDTO[].class, jsonContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (ItemJSONImportDTO itemJSONImportDTO : itemJSONImportDTOS) {
            try {
                this.itemsService.create(itemJSONImportDTO);
                sb.append(String.format("Record %s successfully imported.",
                        itemJSONImportDTO.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String updateItemPrice(String itemName, Double newPrice) {
        StringBuilder sb = new StringBuilder();
        try {
            Double oldPrice = this.itemsService.updateItemPrice(itemName, newPrice);
            sb.append(String.format("%s price updated from $%.2f to $%.2f",
                    itemName, oldPrice, newPrice));
            sb.append(System.lineSeparator());
        } catch (Exception e) {
            sb.append(String.format("Item %s not found!", itemName));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
