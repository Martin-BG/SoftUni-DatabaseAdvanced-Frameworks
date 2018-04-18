package car_dealer.persistance.service.api;

import car_dealer.model.dto.binding.PartDto;
import car_dealer.model.entity.Part;

import java.util.List;

public interface PartService {

    void saveAll(PartDto[] partDtos);

    List<Part> getAllParts();
}
