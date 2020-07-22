package pl.kwolska.playground.adapter.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.kwolska.playground.domain.AccountRepository;
import pl.kwolska.playground.domain.model.Account;
import pl.kwolska.playground.domain.model.Transfer;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.LocalDateTime.now;

@Component
@RequiredArgsConstructor
public class DatabaseAccountRepository implements AccountRepository {

  private final JpaAccountRepository accountRepository;
  private final JpaTransferRepository transferRepository;

  @Override
  public Optional<Account> findAccountById(int accountId) {
    Optional<AccountEntity> accountEntity = accountRepository.findById(accountId);
    return accountEntity.map(account -> {
      List<TransferEntity> creditTransfers = transferRepository.findByCreditAccountId(account.getId());
      List<TransferEntity> debitTransfers = transferRepository.findByDebitAccountId(account.getId());
      List<Transfer> transfers = Stream.of(creditTransfers, debitTransfers)
          .flatMap(Collection::stream)
          .map(this::mapTransferEntity)
          .collect(Collectors.toList());
      return new Account(account.getId(), transfers);
    });
  }

  private Transfer mapTransferEntity(TransferEntity transferEntity) {
    return new Transfer(transferEntity.getId(),
        transferEntity.getDebitAccountId(),
        transferEntity.getCreditAccountId(),
        transferEntity.getMoney(),
        transferEntity.getDate());
  }

  @Override
  public void updateAccount(Account account) {
    Optional<AccountEntity> accountEntity = accountRepository.findById(account.getId());

    accountEntity.ifPresent(accEntity -> {
      List<TransferEntity> transfers = account.getTransfers().stream().map(TransferEntity::new).collect(Collectors.toList());
      transferRepository.saveAll(transfers);
    });
  }

  @Override
  @PostConstruct
  public void createAccounts() {
    AccountEntity accountEntity1 = new AccountEntity();
    AccountEntity accountEntity2 = new AccountEntity();
    accountRepository.save(accountEntity1);
    accountRepository.save(accountEntity2);

    TransferEntity transferEntity1 = new TransferEntity(UUID.randomUUID().toString(), 1, 2, new BigDecimal(500.00), now());
    TransferEntity transferEntity2 = new TransferEntity(UUID.randomUUID().toString(), 2, 1, new BigDecimal(20.00), now());
    transferRepository.saveAll(Arrays.asList(transferEntity1, transferEntity2));
  }

}
