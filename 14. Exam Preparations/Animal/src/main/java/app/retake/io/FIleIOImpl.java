package app.retake.io;

import app.retake.io.api.FileIO;

import java.io.*;

public class FIleIOImpl implements FileIO {

    @Override
    public String read(String file) {
        StringBuilder fileContent = new StringBuilder();

        try (InputStream inputStream = getClass().getResourceAsStream(file);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
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
        try (OutputStream outputStream = new FileOutputStream(file);
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            bufferedWriter.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
