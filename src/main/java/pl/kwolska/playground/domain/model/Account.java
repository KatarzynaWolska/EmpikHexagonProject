package pl.kwolska.playground.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.kwolska.playground.domain.AccountRepository;

import javax.transaction.Transaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


// mozna tylko przelac pieniadze z konta A na konto B, jezeli saldo konta A - kwota transakcji >= 0
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {
  
  private int id;
  private List<Transfer> transfers;
  // lista transferow, balance wynika z transferow,

  // todo operacje dodania pieniedzy na konto/pobrania pieniedzy sa w Account
  // hint: do metod przekazujemy id konta docelowego

  public BigDecimal calculateBalance() {
    BigDecimal balance = BigDecimal.ZERO;
    for (Transfer transfer : transfers) {
      if (transfer.getCreditAccountId() == this.id) {
        balance = balance.subtract(transfer.getMoney());
      } else if (transfer.getDebitAccountId() == this.id) {
        balance = balance.add(transfer.getMoney());
      }
    }
    return balance;
  }
  
  public boolean withdraw(BigDecimal money, int debitAccountId) { //credit
    if (!isTransferImpossible(money)) {
      Transfer transfer = new Transfer(1, debitAccountId, this.id, money, LocalDateTime.now());
      transfers.add(transfer);
      return true;
    }
    return false;
  }
  
  public void deposit(BigDecimal money, int creditAccountId) { //debit
    Transfer transfer = new Transfer(1, this.id, creditAccountId, money, LocalDateTime.now());
    transfers.add(transfer);
  }

  public boolean isTransferImpossible(BigDecimal money) {
    return calculateBalance().subtract(money).compareTo(BigDecimal.ZERO) < 0;
  }
}
