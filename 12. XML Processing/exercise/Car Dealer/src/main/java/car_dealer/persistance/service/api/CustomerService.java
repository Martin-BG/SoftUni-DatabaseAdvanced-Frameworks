package car_dealer.persistance.service.api;

import car_dealer.model.dto.binding.CustomerDto;
import car_dealer.model.dto.view.CustomerByBirthdayDto;
import car_dealer.model.dto.view.CustomerPurchasesViewDto;
import car_dealer.model.entity.Customer;

import java.util.List;

public interface CustomerService {

    void saveAll(CustomerDto[] customerDtos);

    List<Customer> getAllCustomers();

    List<CustomerByBirthdayDto> getAllSortedByBirthday();

    List<CustomerPurchasesViewDto> getCustomersPurchases();
}

