package org.softuni.ruk.parser;

import org.softuni.ruk.parser.interfaces.Parser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@Component(value = "XMLParser")
public class XMLParser implements Parser {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T read(Class<T> objectClass, String fileContent) {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(fileContent));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> String write(T object) {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());

            final Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.displayName());

            final StringWriter writer = new StringWriter();

            marshaller.marshal(object, writer);

            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
