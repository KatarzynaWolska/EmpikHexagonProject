package pl.kwolska.playground.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


// mozna tylko przelac pieniadze z konta A na konto B, jezeli saldo konta A - kwota transakcji >= 0
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {
  
  private int id;
  private BigDecimal balance; // <--- wynika z transferow wszystkich

  public void addToBalance(BigDecimal money) {
    balance.add(money);
  }

  public void subtractFromBalance(BigDecimal money) {
    balance.subtract(money);
  }

  public boolean isTransferImpossible(BigDecimal money) {
    return balance.subtract(money).compareTo(BigDecimal.ZERO) < 0;
  }
}
