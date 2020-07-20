package pl.kwolska.playground.adapter.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
@NoArgsConstructor
@Getter
@Setter
public class TransferEntity {
  
  @Id
  @Column(name = "transfer_id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "debit_account_id")
  private Integer debitAccountId;
  
  @Column(name = "credit_account_id")
  private Integer creditAccountId;
  
  @Column(name = "money")
  private BigDecimal money;
  
  @Column(name = "date")
  private LocalDateTime date;
}
