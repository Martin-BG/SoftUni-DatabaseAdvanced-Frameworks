package app.retake.parser;

import app.retake.parser.interfaces.Parser;
import org.springframework.stereotype.Component;

@Component(value = "JSONParser")
public class JSONParser implements Parser {

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) {
        return null;
    }

    @Override
    public <T> String write(T object) {
        return null;
    }
}
