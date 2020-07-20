package pl.kwolska.playground.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.kwolska.playground.domain.AccountRepository;

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

  public boolean createTransfer(int debitAccountId, BigDecimal money) {
    if (isTransferImpossible(money)) {
      return false;
    }
    Transfer transfer = new Transfer(1, debitAccountId, this.id, money, LocalDateTime.now());
    return true;
  }

  public boolean isTransferImpossible(BigDecimal money) {
    return true;
//    return balance.subtract(money).compareTo(BigDecimal.ZERO) < 0;
  }
}
