package pl.kwolska.playground.adapter.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Getter
@Setter
public class TransferDto {
  
  private Integer debitAccountId;
  private BigDecimal money;
}
