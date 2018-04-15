package product_shop.utils;

import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public final class TextFileUtils {

    private TextFileUtils() {
    }

    public static String read(String path) {
        try (final InputStream inputStream = TextFileUtils.class.getResourceAsStream(path)) {
            return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

//        final StringBuilder sb = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(
//                ResourceUtils.getFile("classpath:" + path)),
//                StandardCharsets.UTF_8))) {
//            reader.lines().forEach(sb::append);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return sb.toString();
    }

    public static void write(String json, String path) {
        try {
            Files.write(
                    ResourceUtils.getFile("classpath:" + path).toPath(),
                    json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
