package pl.kwolska.playground.adapter.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.kwolska.playground.domain.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class AccountDto {
  
  private int id;
  private BigDecimal balance;
  private List<TransferDto> transfers;
}
