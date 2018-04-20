package app.retake.parser;

import app.retake.parser.interfaces.Parser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component(value = "JSONParser")
public class JSONParser implements Parser {

    private Gson gson;

    public JSONParser() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("dd-MM-yyyy")
                .create();
    }

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) {
        return this.gson.fromJson(fileContent, objectClass);
    }

    @Override
    public <T> String write(T object) {
        return this.gson.toJson(object);
    }
}
