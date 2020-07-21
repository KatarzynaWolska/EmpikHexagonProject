package pl.kwolska.playground.domain;

import pl.kwolska.playground.domain.model.Account;

import java.util.Optional;

public interface AccountRepository {
  
  Optional<Account> findAccountById(int accountId);
  
  void updateAccount(Account account);
  
  void createAccounts();
}
