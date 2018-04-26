package org.softuni.ruk.io.impl;

import org.softuni.ruk.io.interfaces.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
public class FileIOImpl implements FileIO {
    @Override
    public String read(String file) {
        StringBuilder fileContent = new StringBuilder();

        try (InputStream inputStream = getClass().getResourceAsStream(file);
             BufferedReader bufferedReader = new BufferedReader(
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent.toString();
    }

    @Override
    public void write(String fileContent, String file) {
        String path = System.getProperty("user.dir") + "/src/main/resources" + file;
        try (OutputStream outputStream = new FileOutputStream(path);
             BufferedWriter bufferedWriter = new BufferedWriter(
                     new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            bufferedWriter.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
