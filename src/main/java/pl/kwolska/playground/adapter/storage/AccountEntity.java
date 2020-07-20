package pl.kwolska.playground.adapter.storage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@Getter
@Setter
public class AccountEntity {
  
  @Id
  @Column(name = "account_id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  
  @OneToMany
  private List<TransferEntity> transfers;
}
