package pl.kwolska.playground.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
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
  
  public Optional<String> withdraw(BigDecimal money, int debitAccountId) { //credit
    if (isTransferImpossible(money)) {
      return Optional.empty();
    }
    Transfer transfer = Transfer.newTransfer(debitAccountId, this.id, money);
    transfers.add(transfer);
    return Optional.of(transfer.getId());
  }
  
  public void deposit(BigDecimal money, int creditAccountId, String transferId) { //debit
    Transfer transfer = Transfer.newExistingTransfer(transferId, this.id, creditAccountId, money);
    transfers.add(transfer);
  }

  public boolean isTransferImpossible(BigDecimal money) {
    return calculateBalance().subtract(money).compareTo(BigDecimal.ZERO) < 0;
  }
}
