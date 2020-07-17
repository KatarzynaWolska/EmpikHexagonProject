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
  private BigDecimal balance; // <--- wynika z transferow wszystkich
  private List<Transfer> transfers;
  private AccountRepository accountRepository;
  // lista transferow, balance wynika z transferow,

  // todo operacje dodania pieniedzy na konto/pobrania pieniedzy sa w Account
  // hint: do metod przekazujemy id konta docelowego
  
  public void executeTransfers() {
    transfers.forEach(t -> {
      if (t.getCredit().equals(this)) {
        subtractMoneyFromBalance(t.getMoney());
      } else {
        addMoneyToBalance(t.getMoney());
      }
    });
  }
  
  public boolean createTransfer(int debitAccountId, BigDecimal money) {
    Optional<Account> debitOptionalAccount = accountRepository.findAccountById(debitAccountId);
    
    if (isTransferImpossible(money) || !debitOptionalAccount.isPresent()) {
      return false;
    }
    
    Account debitAccount = debitOptionalAccount.get();
    
    Transfer transfer = new Transfer(1, debitAccount, this, money, LocalDateTime.now());
    transfers.add(transfer);
    debitAccount.transfers.add(transfer);
    return true;
  }

  // todo nazwa?
  public void addMoneyToBalance(BigDecimal money) {
    this.balance = balance.add(money);
  }

  // todo nazwa?
  public void subtractMoneyFromBalance(BigDecimal money) {
    this.balance = balance.subtract(money);
  }

  public boolean isTransferImpossible(BigDecimal money) {
    return balance.subtract(money).compareTo(BigDecimal.ZERO) < 0;
  }
}
