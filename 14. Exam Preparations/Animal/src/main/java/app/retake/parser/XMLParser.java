package app.retake.parser;

import app.retake.parser.interfaces.Parser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component(value = "XMLParser")
public class XMLParser implements Parser {

    private final DateTimeAdapter dateTimeAdapter;

    public XMLParser() {
        this.dateTimeAdapter = new DateTimeAdapter();
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T> T read(Class<T> objectClass, String fileContent) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setAdapter(this.dateTimeAdapter);
        return (T) unmarshaller.unmarshal(new StringReader(fileContent));
    }

    @Override
    public <T> String write(T object) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.displayName());
        marshaller.setAdapter(this.dateTimeAdapter);
        final StringWriter writer = new StringWriter();
        marshaller.marshal(object, writer);
        return writer.toString();
    }

    @Component
    public static class DateTimeAdapter extends XmlAdapter<String, Date> {
        private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        @Override
        public Date unmarshal(String v) throws Exception {
            return this.format.parse(v);
        }

        @Override
        public String marshal(Date v) {
            return this.format.format(v);
        }
    }
}
