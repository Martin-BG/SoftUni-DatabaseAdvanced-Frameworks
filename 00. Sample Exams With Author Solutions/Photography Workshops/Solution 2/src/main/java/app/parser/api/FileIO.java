package app.parser.api;

import java.io.IOException;

public interface FileIO {
    String read(String fileName) throws IOException;

    void write(String fileName, String content) throws IOException;
}
