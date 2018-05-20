package hiberspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HiberSpringMain {
    public static void main(String[] args) {
        new SpringApplication(HiberSpringMain.class).run(args);
    }
}
