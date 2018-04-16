package car_dealer.persistance.service.api;

import car_dealer.model.dto.view.SaleDetailsViewDto;

import java.util.List;

public interface SaleService {

    void generateSales();

    List<SaleDetailsViewDto> getSalesDetails();
}
