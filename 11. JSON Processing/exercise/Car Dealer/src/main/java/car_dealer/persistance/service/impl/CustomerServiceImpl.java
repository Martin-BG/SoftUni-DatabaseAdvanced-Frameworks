package car_dealer.persistance.service.impl;

import car_dealer.model.dto.binding.CustomerDto;
import car_dealer.model.dto.view.CarDto;
import car_dealer.model.dto.view.CustomerByBirthdayDto;
import car_dealer.model.dto.view.SaleDto;
import car_dealer.model.entity.Customer;
import car_dealer.persistance.repository.CustomerRepository;
import car_dealer.persistance.service.api.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository,
                               final ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAll(final CustomerDto[] customerDtos) {
        Customer[] customers = this.modelMapper.map(customerDtos, Customer[].class);
        this.customerRepository.saveAll(Arrays.asList(customers));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public List<CustomerByBirthdayDto> getAllSortedByBirthday() {
        return this.customerRepository
                .getAllByBirthdate()
                .stream()
                .map(customer -> {
                    CustomerByBirthdayDto customerDto = this.modelMapper.map(customer, CustomerByBirthdayDto.class);
                    customerDto.setPurchases(customer
                            .getPurchases()
                            .stream()
                            .map(sale -> {
                                final SaleDto saleDto = this.modelMapper.map(sale, SaleDto.class);
                                saleDto.setCar(this.modelMapper.map(sale.getCar(), CarDto.class));
                                return saleDto;
                            })
                            .collect(Collectors.toSet()));
                    return customerDto;
                })
                .collect(Collectors.toList());
    }
}
