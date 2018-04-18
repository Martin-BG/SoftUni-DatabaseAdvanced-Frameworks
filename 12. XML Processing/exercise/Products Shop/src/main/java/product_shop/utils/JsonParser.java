package product_shop.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component
public class JsonParser {

    private final Gson gson;

    public JsonParser() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public <T> T objectFromFile(Class<T> tClass, String path) {
        return this.objectFromJson(tClass, TextFileUtils.read(path));
    }

    public <T> void objectToFile(T obj, String path) {
        TextFileUtils.write(objectToJson(obj), path);
    }

    public <T> T objectFromJson(Class<T> tClass, String json) {
        return this.gson.fromJson(json, tClass);
    }

    public <T> String objectToJson(T obj) {
        return this.gson.toJson(obj);
    }
}
