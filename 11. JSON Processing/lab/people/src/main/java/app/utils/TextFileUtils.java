package app.utils;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public final class TextFileUtils {

    private TextFileUtils() {
    }

    public static String read(String path) {
        final StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(
                ResourceUtils.getFile("classpath:" + path)))) {

            reader.lines().forEach(sb::append);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static void write(String json, String path) {
        try {
            Files.write(
                    ResourceUtils.getFile("classpath:" + path).toPath(),
                    json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
//                new FileOutputStream(ResourceUtils.getFile("classpath:" + path)), StandardCharsets.UTF_8))) {
//            writer.write(json);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
