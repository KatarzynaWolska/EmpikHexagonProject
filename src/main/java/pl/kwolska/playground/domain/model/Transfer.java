package pl.kwolska.playground.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.kwolska.playground.domain.model.Account;

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
  
}
