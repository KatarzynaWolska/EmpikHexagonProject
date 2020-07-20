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


@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {
  
  private final int id;
  private final List<Transfer> transfers;

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
    if (isTransferImpossible(money)) {
      return false;
    }
    Transfer transfer = Transfer.of(debitAccountId, this.id, money);
    transfers.add(transfer);
    return true;
  }
  
  public void deposit(BigDecimal money, int creditAccountId) { //debit
    Transfer transfer = Transfer.of(this.id, creditAccountId, money);
    transfers.add(transfer);
  }

  public boolean isTransferImpossible(BigDecimal money) {
    return calculateBalance().subtract(money).compareTo(BigDecimal.ZERO) < 0;
  }
}
