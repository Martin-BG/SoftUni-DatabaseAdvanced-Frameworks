package app.exam.service.api;

import app.exam.domain.dto.json.ItemJSONImportDTO;

public interface ItemsService {
    void create(ItemJSONImportDTO itemJSONImportDTO);

    Double updateItemPrice(String itemName, Double price);
}
