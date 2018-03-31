package app;

import app.models.Account;
import app.models.User;
import app.services.account.AccountServiceImpl;
import app.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserServiceImpl userService;
    private final AccountServiceImpl accountService;

    @Autowired
    public ConsoleRunner(final UserServiceImpl userService, final AccountServiceImpl accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(final String... args) {
        User user = new User();
        user.setUserName("example");
        user.setAge(23);
        user.setAccounts(new HashSet<>());

        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(25000));
        account.setUser(user);

        user.getAccounts().add(account);

        this.userService.registerUser(user);

        this.accountService.withdrawMoney(BigDecimal.valueOf(20000), user.getId());
        this.accountService.transferMoney(BigDecimal.valueOf(10000), user.getId());
    }
}
