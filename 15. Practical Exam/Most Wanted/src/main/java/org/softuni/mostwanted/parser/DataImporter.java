package org.softuni.mostwanted.parser;

import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.persistance.service.api.Creatable;
import org.springframework.stereotype.Component;

@Component
public class DataImporter {

    public <T> String importData(final T[] dtoArray, final Creatable service) {
        final StringBuilder sb = new StringBuilder();
        for (T dto : dtoArray) {
            sb.append(service.create(dto)).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    public <T> String importData(final Parser parser,
                                 final Class<T[]> clazz,
                                 final String content,
                                 final Creatable service) {
        return this.importData(
                parser.read(clazz, content),
                service);
    }
}
