package car_dealer.terminal;

import car_dealer.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;

@Controller
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources/";
    private static final String SEED_SUPLIES_JSON = RESOURCES_PATH + "seed/supplies.json";
    private static final String SEED_PARTS_JSON = RESOURCES_PATH + "seed/parts.json";
    private static final String SEED_CARS_JSON = RESOURCES_PATH + "seed/cars.json";
    private static final String SEED_CUSTOMERS_JSON = RESOURCES_PATH + "seed/customers.json";

    private final JsonParser jsonParser;

    @Autowired
    public Terminal(final JsonParser jsonParser) {

        this.jsonParser = jsonParser;
    }

    @Override
    public void run(final String... args) {
        seedDatabase();

    }

    private void seedDatabase() {
//        UserDto[] usersDto = this.jsonParser.objectFromFile(UserDto[].class, SEED_SUPLIES_JSON);
//        this.userService.saveAll(usersDto);
    }

}
