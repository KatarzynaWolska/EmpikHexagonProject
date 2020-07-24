package pl.kwolska.playground.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Transfer {

  private final String id;
  private final int debitAccountId;
  private final int creditAccountId;
  private final BigDecimal money;
  private final LocalDateTime date;

  public static Transfer newTransfer(int debitAccountId, int creditAccountId, BigDecimal money) {
    return new Transfer(UUID.randomUUID().toString(), debitAccountId, creditAccountId, money, LocalDateTime.now());
  }

  public static Transfer newExistingTransfer(String id, int debitAccountId, int creditAccountId, BigDecimal money) {
    return new Transfer(id, debitAccountId, creditAccountId, money, LocalDateTime.now());
  }
}
