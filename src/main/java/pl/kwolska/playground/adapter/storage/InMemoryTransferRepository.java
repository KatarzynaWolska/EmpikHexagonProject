package pl.kwolska.playground.adapter.storage;

import org.springframework.stereotype.Component;
import pl.kwolska.playground.domain.model.Account;
import pl.kwolska.playground.domain.model.Transfer;
import pl.kwolska.playground.domain.TransferRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
class InMemoryTransferRepository implements TransferRepository {
  
  private final List<Transfer> transfers = new ArrayList<>();
  
  @Override
  public void addTransfer(Transfer transfer) {
    transfers.add(transfer);
  }
  
  @Override
  public List<Transfer> findAccountTransfers(Account account) {
    return transfers.stream()
        .filter(t -> t.getCredit().equals(account))
        .collect(Collectors.toList());
  }
}
