package pl.kwolska.playground.adapter.storage;

import org.springframework.stereotype.Component;
import pl.kwolska.playground.domain.model.Account;
import pl.kwolska.playground.domain.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@Component
class InMemoryAccountRepository implements AccountRepository {

  private final List<Account> accounts = new ArrayList<>();
  
  @Override
  public Optional<Account> findAccountById(int accountId) {
    return accounts.stream().filter(acc -> acc.getId() == accountId).findFirst();
  }
  
  @Override
  public void updateAccount(Account account) {
  }
  
  @Override
  public void createAccounts() {
    accounts.add(new Account(1, Collections.emptyList()));
    accounts.add(new Account(2, Collections.emptyList()));
  }
}
