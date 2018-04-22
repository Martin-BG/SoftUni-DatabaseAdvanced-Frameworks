package app.retake.parser.interfaces;

import javax.xml.bind.JAXBException;

public interface Parser {

    <T> T read(Class<T> objectClass, String fileContent) throws JAXBException;

    <T> String write(T object) throws JAXBException;
}
