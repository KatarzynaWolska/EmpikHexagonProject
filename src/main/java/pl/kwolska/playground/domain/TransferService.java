package pl.kwolska.playground.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kwolska.playground.adapter.storage.AccountEntity;
import pl.kwolska.playground.domain.model.Account;
import pl.kwolska.playground.domain.model.Transfer;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TransferService {
  
  private final AccountRepository accountRepository;
  
  public void createAccounts() {
    accountRepository.createAccounts();
  }
  
  public boolean createTransfer(Account debit, Account credit, BigDecimal money) {
    Optional<String> transferId = credit.withdraw(money, debit.getId());

    if (transferId.isPresent()) {
      debit.deposit(money, credit.getId(), transferId.get());
      return true;
    }
    return false;
  }

  public void createTransfer(int debitAccountId, int creditAccountId, BigDecimal money) throws AccountNotFoundException {
    Account debitAccount = accountRepository.findAccountById(debitAccountId).orElseThrow(AccountNotFoundException::new);
    Account creditAccount = accountRepository.findAccountById(creditAccountId).orElseThrow(AccountNotFoundException::new);
    
    if(createTransfer(debitAccount, creditAccount, money)) {
      accountRepository.updateAccount(debitAccount);
      accountRepository.updateAccount(creditAccount);
    }
  }
  
  public List<Transfer> findAccountTransfers(int accountId) {
    return accountRepository
        .findAccountById(accountId)
        .map(Account::getTransfers)
        .orElse(Collections.emptyList());
  }
  
  public Optional<Account> findAccountById(int accountId) {
    return accountRepository.findAccountById(accountId);
  }
}
