package app.parser.api;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface Serializer {

    <T> T deserialize(Class<T> className, String fileName) throws IOException, JAXBException;

    <T> String serialize(T t, String fileNameW) throws JAXBException, IOException;

    <T> String serialize(T t) throws JAXBException;
}
