package pl.kwolska.playground.adapter.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kwolska.playground.domain.model.Transfer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "transfers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferEntity {
  
  @Id
  @Column(name = "transfer_id")
  private String id = UUID.randomUUID().toString();
  
  @Column(name = "debit_account_id")
  private Integer debitAccountId;
  
  @Column(name = "credit_account_id")
  private Integer creditAccountId;
  
  @Column(name = "money")
  private BigDecimal money;
  
  @Column(name = "date")
  private LocalDateTime date;

  public TransferEntity(Transfer transfer) {
    this.id = Optional.ofNullable(transfer.getId()).orElse(UUID.randomUUID().toString());
    this.debitAccountId = transfer.getDebitAccountId();
    this.creditAccountId = transfer.getCreditAccountId();
    this.money = transfer.getMoney();
    this.date = transfer.getDate();
  }
}
