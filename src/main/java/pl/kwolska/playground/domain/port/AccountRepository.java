package pl.kwolska.playground.domain.port;

import pl.kwolska.playground.domain.model.Account;

public interface AccountRepository {
  
  Account findAccountById(int accountId);
}
