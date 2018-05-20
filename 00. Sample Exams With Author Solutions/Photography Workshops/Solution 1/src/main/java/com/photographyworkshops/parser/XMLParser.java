package com.photographyworkshops.parser;
import com.photographyworkshops.parser.interfaces.FileParser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component(value = "XMLParser")
public class XMLParser implements FileParser {

    private JAXBContext jaxbContext;

    @Override
    public <T> T read(Class<T> objectClass, String file) throws IOException, JAXBException {
        this.jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
        T object;
        try (
                InputStream inputStream = getClass().getResourceAsStream(file);
        ) {
            object = (T) unmarshaller.unmarshal(inputStream);
        }

        return object;
    }

    @Override
    public <T> void write(T object, String file) throws IOException, JAXBException {
        this.jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = this.jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        try (
                OutputStream outputStream = new FileOutputStream(file);
        ) {
            marshaller.marshal(object, outputStream);
        }
    }
}
