package org.softuni.ruk.persistance.service.api;

import org.softuni.ruk.model.dto.view.json.EmployeesWithClientsDto;

import java.util.List;

public interface EmployeeService extends Creatable {

    List<EmployeesWithClientsDto> getEmployeesByClients();
}
