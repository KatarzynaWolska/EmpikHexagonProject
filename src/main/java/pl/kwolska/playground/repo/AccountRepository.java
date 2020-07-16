package pl.kwolska.playground.repo;

import pl.kwolska.playground.domain.Account;

public interface AccountRepository {
  
  Account findAccountById(int accountId);
}
