package car_dealer.persistance.service.api;

import car_dealer.model.dto.binding.SupplierDto;
import car_dealer.model.dto.view.SupplierViewDto;
import car_dealer.model.entity.Supplier;

import java.util.List;

public interface SupplierService {

    void saveAll(SupplierDto[] supplierDtos);

    List<Supplier> getAllSuppliers();

    List<SupplierViewDto> getLocalSuppliers();
}
