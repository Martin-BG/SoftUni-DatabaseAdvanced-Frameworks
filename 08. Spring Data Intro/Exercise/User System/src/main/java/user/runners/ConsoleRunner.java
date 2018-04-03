package user.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import user.models.entities.User;
import user.services.api.UserService;

import java.util.Date;

@SpringBootApplication
@Controller
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public ConsoleRunner(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(final String... args) {
        if (this.userService.getUsersCount() == 0L) {
            addUsers(1000);
        }
        printAllUsersByEmailProvider("abv.bg");
        deactivateUsersInactiveSinceDate(new Date());
        printAllUsersByAgeRange(20, 25);
    }

    private void printAllUsersByAgeRange(int lowBound, int highBound) {
        this.userService.getUserNamesAndAgeByAgeRange(lowBound, highBound)
                .forEach(System.out::println);
    }

    private void deactivateUsersInactiveSinceDate(final Date date) {
        this.userService.deactivateInactiveUsers(date);
    }

    private void printAllUsersByEmailProvider(final String provider) {
        for (User user : this.userService.getAllUsersByEmailProvider(provider)) {
            System.out.println(user.getUserName() + " " + user.getEmail());
        }
    }

    private void addUsers(final int count) {
        for (int i = 1; i <= count; i++) {
            User user = new User();
            user.setUserName("username" + i);
            user.setPassword("pasSword%" + i);
            user.setEmail("mail" + i + "x@abv.bg");
            user.setAge(i % 120 + 1);
            user.setFirstName("First" + i);
            user.setLastName("Last" + i);
            user.setRegisteredOn(new Date());
            user.setLastTimeLoggedIn(new Date());
            user.setDeleted(false);
            this.userService.save(user);
        }
    }

}
