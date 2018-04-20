package app.exam.service.api;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;

public interface EmployeeService {
    void create(EmployeeJSONImportDTO importDTO);

    void createMany(EmployeeJSONImportDTO[] importDTO);
}
