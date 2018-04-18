package car_dealer.persistance.service.impl;

import car_dealer.model.dto.binding.SupplierDto;
import car_dealer.model.dto.view.SupplierViewDto;
import car_dealer.model.entity.Supplier;
import car_dealer.persistance.repository.SupplierRepository;
import car_dealer.persistance.service.api.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(final SupplierRepository supplierRepository,
                               final ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAll(final SupplierDto[] supplierDtos) {
        Supplier[] suppliers = this.modelMapper.map(supplierDtos, Supplier[].class);
        this.supplierRepository.saveAll(Arrays.asList(suppliers));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return this.supplierRepository.findAll();
    }

    @Override
    public List<SupplierViewDto> getLocalSuppliers() {
        return this.supplierRepository.getLocalSuppliers();
//                .stream()
//                .map(supplier -> {
//                    final SupplierViewDto supplierDto = this.modelMapper.map(supplier, SupplierViewDto.class);
//                    supplierDto.setPartsCount(supplier.getParts().size());
//                    return supplierDto;
//                })
//                .collect(Collectors.toList());
    }
}
