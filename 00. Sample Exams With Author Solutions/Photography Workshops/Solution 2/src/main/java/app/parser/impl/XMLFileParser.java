package app.parser.impl;

import app.parser.api.Serializer;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component(value = "XMLParser")
public class XMLFileParser implements Serializer {
    private JAXBContext jaxbContext;

    @Override
    public <T> T deserialize(Class<T> className, String fileName) throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(className);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        T object = (T) unmarshaller.unmarshal(new File(fileName));
        return object;
    }


    @Override
    public <T> String serialize(T t, String fileName) throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(t.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(t, new File(fileName));
        return null;
    }

    @Override
    public <T> String serialize(T t) throws JAXBException {
        return null;
    }
}
