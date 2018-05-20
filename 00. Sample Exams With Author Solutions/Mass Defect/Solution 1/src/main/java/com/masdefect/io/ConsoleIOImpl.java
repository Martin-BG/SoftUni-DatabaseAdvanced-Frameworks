package com.masdefect.io;

import com.masdefect.io.interfaces.ConsoleIO;
import org.springframework.stereotype.Component;

@Component
public class ConsoleIOImpl implements ConsoleIO {
    @Override
    public void write(String line) {
        System.out.println(line);
    }
}
