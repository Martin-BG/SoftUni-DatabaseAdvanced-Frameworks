package game_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"game_store"})
public class GameStore {

    public static void main(final String[] args) {
        SpringApplication.run(GameStore.class, args);
    }
}
