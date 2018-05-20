package hiberspring.serializers.impl;

import hiberspring.exceptions.DeserializeException;
import hiberspring.exceptions.SerializeException;
import hiberspring.io.FileParser;
import hiberspring.serializers.Serializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component("xml")
public class XmlSerializer implements Serializer {

    private final FileParser fileParser;

    @Autowired
    public XmlSerializer(FileParser fileParser) throws JAXBException {
        this.fileParser = fileParser;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(Class<T> tClass, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            try (InputStream inputStream = tClass.getResourceAsStream(fileName);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String currentXml = sb.toString();
                File file = new File(System.getProperty("user.dir") + "/src/main/resources" + fileName);
                if (currentXml.indexOf("<?xml") > 0) {
                    String validXml = currentXml.substring(currentXml.indexOf("<?xml"));
                    String correctFileName = fileName.replace(".xml", "Valid.xml");
                    this.fileParser.writeFile("/src/main/resources" + correctFileName, validXml);
                    file = new File(System.getProperty("user.dir") + "/src/main/resources" + correctFileName);
                }
                return (T) unmarshaller.unmarshal(file);
            }
        } catch (JAXBException e) {
            throw new DeserializeException("Could not deserialize to class" + tClass, e);
        } catch (IOException ioe) {
            throw new DeserializeException("Could not deserialize to class" + tClass + " Unable to read from file " + fileName, ioe);
        }
    }

    @Override
    public <T> void serialize(T object, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            String path = System.getProperty("user.dir") + File.separator + fileName;
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            try (OutputStream outputStream = new FileOutputStream(fileName);
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
                writer.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
                marshaller.marshal(object, writer);

            }

        } catch (JAXBException e) {
            throw new SerializeException("Could not serialize " + object, e);
        } catch (IOException ioe) {
            throw new SerializeException("Could not serialize " + object + " Unable to write in file " + fileName, ioe);
        }
    }
}