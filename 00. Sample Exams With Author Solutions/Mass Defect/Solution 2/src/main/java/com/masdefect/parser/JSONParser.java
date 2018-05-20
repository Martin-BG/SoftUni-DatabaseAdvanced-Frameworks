package com.masdefect.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.masdefect.io.interfaces.FileIO;
import com.masdefect.parser.interfaces.FileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements FileParser {

    private Gson gson;

    @Autowired
    private FileIO fileIO;

    public JSONParser() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException {
        T object = this.gson.fromJson(fileContent, objectClass);
        return object;
    }

    @Override
    public <T> String write(T object) throws IOException {
        String jsonContent = this.gson.toJson(object);
        return jsonContent;
    }
}
