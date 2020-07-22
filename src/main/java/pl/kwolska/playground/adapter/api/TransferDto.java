package pl.kwolska.playground.adapter.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransferDto {
  
  private Integer debitAccountId;
  private Integer creditAccountId;
  private BigDecimal money;
}
