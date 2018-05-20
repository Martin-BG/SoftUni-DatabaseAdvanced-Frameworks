package com.masdefect.parser;

import com.masdefect.parser.interfaces.FileParser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component(value = "XMLParser")
public class XMLParser implements FileParser {

    private JAXBContext jaxbContext;

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        this.jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
        T object;
        try (
                InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        ) {
            object = (T) unmarshaller.unmarshal(inputStream);
        }
        return object;
    }

    @Override
    public <T> String write(T object) throws IOException, JAXBException {
        this.jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = this.jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(object, sw);

        String result = sw.toString();
        return result;
    }
}
