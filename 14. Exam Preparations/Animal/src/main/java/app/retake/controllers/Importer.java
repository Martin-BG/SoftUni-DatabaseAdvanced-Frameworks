package app.retake.controllers;

import app.retake.domain.dto.Importable;
import app.retake.parser.ValidationUtil;
import app.retake.services.api.Creatable;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotNull;

@Controller
class Importer {

    <T extends Importable> String persist(@NotNull T[] dtos, @NotNull Creatable repository) {
        final StringBuilder sb = new StringBuilder();
        for (T dto : dtos) {
            if (ValidationUtil.isValid(dto)) {
                repository.create(dto);
                sb.append(dto.successMessage()).append(System.lineSeparator());
            } else {
                sb.append(dto.errorMessage()).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
