package app.parser.impl;

import app.parser.api.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIOImpl implements FileIO {

    @Override
    public String read(String fileName) throws IOException {
        InputStream str = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(str));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    public void write(String fileName, String content) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(fileName);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            writer.write(content);
        }
    }
}
