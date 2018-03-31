package app.services.account;

import app.models.Account;
import app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements AccountService {

    final private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(final BigDecimal money, final Long id) {
        Account account = getAccountAndValidateMoney(money, id);

        if (account.getBalance().compareTo(money) < 0) {    // Redundant - setter prevents this
            throw new IllegalArgumentException("Not enough money");
        }

        account.setBalance(account.getBalance().subtract(money));

        this.accountRepository.save(account);
    }

    @Override
    public void transferMoney(final BigDecimal money, final Long id) {
        Account account = getAccountAndValidateMoney(money, id);

        account.setBalance(account.getBalance().add(money));

        this.accountRepository.save(account);
    }

    private Account getAccountAndValidateMoney(final BigDecimal money, final Long id) {
        if (BigDecimal.ZERO.compareTo(money) > 0) {
            throw new IllegalArgumentException("Money value should be positive");
        }

        Account account = this.accountRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));

        if (account.getUser() == null) {    // Redundant - user field is not nullable in model
            throw new RuntimeException("Account doesn't belong to any user");
        }

        return account;
    }
}
