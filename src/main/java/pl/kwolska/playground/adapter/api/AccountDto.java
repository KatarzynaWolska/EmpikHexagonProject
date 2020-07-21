package pl.kwolska.playground.adapter.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.kwolska.playground.domain.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AccountDto {
  
  private int id;
  private BigDecimal balance;
  private List<Transfer> transfers;
}
