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
import java.text.SimpleDateFormat;
import java.util.Date;

@Component(value = "XMLParser")
public class XMLParser implements Parser {

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setAdapter(new DateTimeAdapter());
        return (T) unmarshaller.unmarshal(new StringReader(fileContent));
    }

    @Override
    public <T> String write(T object) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();
        marshaller.marshal(object, writer);
        return writer.toString();
    }

    public class DateTimeAdapter extends XmlAdapter<String, Date> {
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
