package car_dealer.utils;

import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public final class TextFileUtils {

    private TextFileUtils() {
    }

    public static String read(String path) {
        try (final InputStream inputStream = new FileInputStream(path)) {
            return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void write(String text, String path) {
        try {
            Files.write(
                    ResourceUtils.getFile(path).toPath(),
                    text.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
