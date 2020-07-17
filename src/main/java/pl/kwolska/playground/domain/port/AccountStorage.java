package pl.kwolska.playground.domain.port;

import pl.kwolska.playground.domain.model.Account;

public interface AccountStorage {
  
  Account findAccountById(int accountId);
}
