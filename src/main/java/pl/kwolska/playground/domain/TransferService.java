package pl.kwolska.playground.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kwolska.playground.repo.AccountRepository;
import pl.kwolska.playground.repo.TransferRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class TransferService {
  
  @Autowired
  private TransferRepository transferRepository;
  
  @Autowired
  private AccountRepository accountRepository;
  
  
  public void createTransfer(Account debit, Account credit, BigDecimal money) {
    calculateDifference(debit, credit, money);
    Transfer transfer = new Transfer(1, debit, credit, money, LocalDateTime.now());
    transferRepository.addTransfer(transfer);
  }
  
  public void createTransfer(int debitAccountId, int creditAccountId, BigDecimal money) {
    Account debitAccount = accountRepository.findAccountById(debitAccountId);
    Account creditAccount = accountRepository.findAccountById(creditAccountId);
    createTransfer(debitAccount, creditAccount, money);
  }
  
  private void calculateDifference(Account debit, Account credit, BigDecimal money) {
    BigDecimal creditBalance = credit.getBalance();
    BigDecimal debitBalance = debit.getBalance();
  
    credit.setBalance(creditBalance.subtract(money));
    debit.setBalance(debitBalance.add(money));
  }
  
  public List<Transfer> getAccountTransfers(int accountId) {
    Account account = accountRepository.findAccountById(accountId);
    
    if (account == null) {
      return Collections.emptyList();
    } else {
      return transferRepository.getAccountTransfers(account);
    }
  }
}
