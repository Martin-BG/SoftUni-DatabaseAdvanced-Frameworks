package car_dealer.persistance.service.impl;

import car_dealer.model.dto.binding.CustomerDto;
import car_dealer.model.dto.view.CarViewDto;
import car_dealer.model.dto.view.CustomerByBirthdayDto;
import car_dealer.model.dto.view.CustomerPurchasesViewDto;
import car_dealer.model.dto.view.SaleViewDto;
import car_dealer.model.entity.Customer;
import car_dealer.model.entity.Part;
import car_dealer.persistance.repository.CustomerRepository;
import car_dealer.persistance.service.api.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
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
                                final SaleViewDto saleViewDto = this.modelMapper.map(sale, SaleViewDto.class);
                                saleViewDto.setCar(this.modelMapper.map(sale.getCar(), CarViewDto.class));
                                return saleViewDto;
                            })
                            .collect(Collectors.toSet()));
                    return customerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerPurchasesViewDto> getCustomersPurchases() {
        return this.customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerPurchasesViewDto customerDto = new CustomerPurchasesViewDto();
                    customerDto.setName(customer.getName());
                    customerDto.setBoughtCars(customer.getPurchases().size());

                    BigDecimal moneySpent = customer.getPurchases()
                            .stream()
                            .map(sale -> sale.getCar()
                                    .getParts()
                                    .stream()
                                    .map(Part::getPrice)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                            )
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    customerDto.setSpentMoney(moneySpent);
                    return customerDto;
                })
                .sorted((c1, c2) -> {
                    int cmp = c2.getSpentMoney().compareTo(c1.getSpentMoney());
                    if (cmp == 0) {
                        cmp = c2.getBoughtCars().compareTo(c1.getBoughtCars());
                    }
                    return cmp;
                })
                .collect(Collectors.toList());
    }
}
