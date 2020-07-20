package pl.kwolska.playground.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Transfer {
  
  private final int id;
  private final int debitAccountId;
  private final int creditAccountId;
  private final BigDecimal money;
  private final LocalDateTime date;
  
  public static Transfer of (int debitAccountId, int creditAccountId, BigDecimal money) {
    return new Transfer(1, debitAccountId, creditAccountId, money, LocalDateTime.now());
  }
}
