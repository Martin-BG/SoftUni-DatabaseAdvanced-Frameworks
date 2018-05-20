package app.parser.impl;

import app.parser.api.FileIO;
import app.parser.api.Serializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component(value = "JSONParser")
public class JSONSerializer implements Serializer {
    private Gson gson;
    private FileIO fileIOUtil;

    public JSONSerializer() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        this.fileIOUtil = new FileIOImpl();
    }

    @Override
    public <T> T deserialize(Class<T> className, String fileName) throws IOException {
        String content = this.fileIOUtil.read(fileName);
        T mapped = gson.fromJson(content, className);
        return mapped;
    }

    @Override
    public <T> String serialize(T t, String fileName) throws IOException {
        String content = gson.toJson(t);
        //this.fileIOUtil.write(content, fileName);
        return content;
    }

    @Override
    public <T> String serialize(T t) {
        return null;
    }
}
