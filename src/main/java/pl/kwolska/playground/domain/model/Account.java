package pl.kwolska.playground.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {
  
  private int id;
  private BigDecimal balance;
}
