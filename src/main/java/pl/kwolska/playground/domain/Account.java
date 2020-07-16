package pl.kwolska.playground.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Account {
  
  private int id;
  private BigDecimal balance;
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return id == account.id;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
