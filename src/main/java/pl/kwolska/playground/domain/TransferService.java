package pl.kwolska.playground.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kwolska.playground.domain.model.Account;
import pl.kwolska.playground.domain.model.Transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransferService {
  
  private final TransferRepository transferRepository;
  
  private final AccountRepository accountRepository;
  
  public boolean createTransfer(Account debit, Account credit, BigDecimal money) {
    if (isTransferImpossible(credit, money)) {
      return false;
    }
    credit.subtractFromBalance(money);
    debit.addToBalance(money);
    Transfer transfer = new Transfer(1, debit, credit, money, LocalDateTime.now());
    transferRepository.addTransfer(transfer);
    return true;
  }

  private boolean isTransferImpossible(Account credit, BigDecimal money) {
    return credit.getBalance().subtract(money).compareTo(BigDecimal.ZERO) < 0;
  }

  public void createTransfer(int debitAccountId, int creditAccountId, BigDecimal money) {
    Optional<Account> debitAccount = accountRepository.findAccountById(debitAccountId);
    Optional<Account> creditAccount = accountRepository.findAccountById(creditAccountId);
    
    if (debitAccount.isPresent() && creditAccount.isPresent()) {
      createTransfer(debitAccount.get(), creditAccount.get(), money);
    }
  }
  
  public List<Transfer> findAccountTransfers(int accountId) {
    Optional<Account> account = accountRepository.findAccountById(accountId);
    
    if (account.isPresent()) {
      return transferRepository.findAccountTransfers(account.get());
    } else {
      return Collections.emptyList();
    }
  }
}
