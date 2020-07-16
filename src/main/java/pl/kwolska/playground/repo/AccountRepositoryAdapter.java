package pl.kwolska.playground.repo;

import org.springframework.stereotype.Component;
import pl.kwolska.playground.domain.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AccountRepositoryAdapter implements AccountRepository {
  
  private final List<Account> accounts = new ArrayList<>();
  
  public AccountRepositoryAdapter() {
    accounts.add(new Account(1, new BigDecimal(1000)));
    accounts.add(new Account(2, new BigDecimal(2000)));
  }
  
  @Override
  public Account findAccountById(int accountId) {
    Optional<Account> account = accounts.stream().filter(acc -> acc.getId() == accountId).findFirst();
    return account.orElse(null);
  }
}
