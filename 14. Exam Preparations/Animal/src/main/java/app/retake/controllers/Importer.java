package app.retake.controllers;

import app.retake.domain.dto.Importable;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.Creatable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
final class Importer {

    @Validated
    <T extends Importable> String importData(final @NotNull Parser parser,
                                             final @NotNull T[] t,
                                             final @NotNull String content,
                                             final @NotNull Creatable service) {
        try {
            return this.persist(
                    parser.read(t.getClass(), content),
                    service);
        } catch (IOException | JAXBException e) {
            return e.getMessage();
        }
    }

    private <T extends Importable> String persist(final @NotNull T[] dtos,
                                                  final @NotNull Creatable service) {
        final StringBuilder sb = new StringBuilder();
        for (T dto : dtos) {
            if (ValidationUtil.isValid(dto)) {
                service.create(dto);
                sb.append(dto.successMessage()).append(System.lineSeparator());
            } else {
                sb.append(dto.errorMessage()).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
