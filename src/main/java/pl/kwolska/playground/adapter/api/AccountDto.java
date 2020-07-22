package pl.kwolska.playground.adapter.api;

import lombok.Data;
import pl.kwolska.playground.domain.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountDto {
  
  private int id;
  private BigDecimal balance;
  private List<Transfer> transfers;
}
