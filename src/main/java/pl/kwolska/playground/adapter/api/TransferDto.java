package pl.kwolska.playground.adapter.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TransferDto {
  
  private Integer debitAccountId;
  private BigDecimal money;
}
