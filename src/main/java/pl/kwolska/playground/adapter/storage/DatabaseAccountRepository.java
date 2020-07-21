package pl.kwolska.playground.adapter.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.kwolska.playground.domain.AccountRepository;
import pl.kwolska.playground.domain.model.Account;
import pl.kwolska.playground.domain.model.Transfer;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Profile("database")
@Component
@RequiredArgsConstructor
public class DatabaseAccountRepository implements AccountRepository {
  
  private final JpaAccountRepository accountRepository;
  
  
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
  
  @Override
  public void updateAccount(Account account) {
    Optional<AccountEntity> accountEntity = accountRepository.findById(account.getId());
  
    accountEntity.ifPresent(accEntity -> {
      List<TransferEntity> transfers = account.getTransfers().stream().map(transfer -> new TransferEntity(
          transfer.getId(),
          transfer.getDebitAccountId(),
          transfer.getCreditAccountId(),
          transfer.getMoney(),
          transfer.getDate())).collect(Collectors.toList());
      accEntity.setTransfers(transfers);
      accountRepository.save(accEntity);
    });
  }
  
  @Override
  public void createAccounts() {
    AccountEntity accountEntity1 = new AccountEntity();
    AccountEntity accountEntity2 = new AccountEntity();
    accountRepository.save(accountEntity1);
    accountRepository.save(accountEntity2);
  }
  
}
