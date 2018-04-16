package car_dealer.persistance.service.impl;

import car_dealer.model.dto.binding.PartDto;
import car_dealer.model.entity.Part;
import car_dealer.model.entity.Supplier;
import car_dealer.persistance.repository.PartRepository;
import car_dealer.persistance.service.api.PartService;
import car_dealer.persistance.service.api.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final SupplierService supplierService;

    @Autowired
    public PartServiceImpl(final PartRepository partRepository,
                           final ModelMapper modelMapper,
                           final SupplierService supplierService) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.supplierService = supplierService;
    }

    @Override
    public void saveAll(final PartDto[] partDtos) {
        final Part[] parts = this.modelMapper.map(partDtos, Part[].class);

        final Random random = new Random();
        final List<Supplier> allSuppliers = this.supplierService.getAllSuppliers();
        for (final Part part : parts) {
            part.setSupplier(allSuppliers.get(random.nextInt(allSuppliers.size())));
        }

        this.partRepository.saveAll(Arrays.asList(parts));
    }

    @Override
    public List<Part> getAllParts() {
        return this.partRepository.findAll();
    }
}
