package pl.kwolska.playground.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class Transfer {
  
  private int id;
  private int debitAccountId;
  private int creditAccountId;
  private BigDecimal money;
  private LocalDateTime date;
  
  public static Transfer of (int debitAccountId, int creditAccountId, BigDecimal money) {
    return new Transfer(1, debitAccountId, creditAccountId, money, LocalDateTime.now());
  }
  
}
