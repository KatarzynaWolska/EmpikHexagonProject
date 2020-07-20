package pl.kwolska.playground.adapter.storage;

import pl.kwolska.playground.domain.AccountRepository;
import pl.kwolska.playground.domain.model.Account;
import pl.kwolska.playground.domain.model.Transfer;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabaseAccountRepository implements AccountRepository {
  
  private JpaAccountRepository accountRepository;
  
  @PostConstruct
  public void createAccounts() {
    AccountEntity accountEntity1 = new AccountEntity();
    AccountEntity accountEntity2 = new AccountEntity();
    accountRepository.save(accountEntity1);
    accountRepository.save(accountEntity2);
  }
  
  @Override
  public Optional<Account> findAccountById(int accountId) {
    Optional<AccountEntity> accountEntity = accountRepository.findById(accountId);
    return accountEntity.map(accEntity -> {
      List<Transfer> transfers = accEntity.getTransfers().stream().map(transferEntity -> new Transfer(transferEntity.getId(),
          transferEntity.getDebitAccountId(),
          transferEntity.getCreditAccountId(),
          transferEntity.getMoney(),
          transferEntity.getDate())).collect(Collectors.toList());
      return new Account(accEntity.getId(), transfers);
    });
  }
}
