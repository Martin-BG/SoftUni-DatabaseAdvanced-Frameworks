package org.softuni.mostwanted.io.interfaces;

public interface FileIO {
    String read(String file);

    void write(String fileContent, String file);
}
