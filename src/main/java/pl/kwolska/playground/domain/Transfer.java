package pl.kwolska.playground.domain;

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
  private Account debit;
  private Account credit;
  private BigDecimal money;
  private LocalDateTime date;
  
}
