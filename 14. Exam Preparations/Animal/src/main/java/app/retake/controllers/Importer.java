package app.retake.controllers;

import app.retake.domain.dto.Importable;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.Creatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
final class Importer {

    private final ValidationUtil validator;

    @Autowired
    Importer(final ValidationUtil validator) {
        this.validator = validator;
    }

    <T extends Importable> String importData(final Parser parser,
                                             final Class<T[]> clazz,
                                             final String content,
                                             final Creatable service) {
        try {
            return this.persist(
                    parser.read(clazz, content),
                    service);
        } catch (IOException | JAXBException e) {
            return e.toString();
        }
    }

    <T extends Importable> String persist(final T[] dtos,
                                          final Creatable service) {
        final StringBuilder sb = new StringBuilder();
        for (T dto : dtos) {
            if (this.validator.isValid(dto)) {
                try {
                    service.create(dto);
                    sb.append(dto.successMessage()).append(System.lineSeparator());
                } catch (IllegalArgumentException e) {
                    sb.append(dto.errorMessage()).append(System.lineSeparator());
                }
            } else {
                sb.append(dto.errorMessage()).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
