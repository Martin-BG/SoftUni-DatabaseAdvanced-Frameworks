package hiberspring.io;

import org.springframework.stereotype.Component;

@Component
public class ConsoleWriter implements Writer {

    public ConsoleWriter() {
    }

    @Override
    public void println(String content) {
        System.out.println(content);
    }

    @Override
    public void println(String format, Object... args) {
        System.out.println(String.format(format, args));
    }
}
