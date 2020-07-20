package pl.kwolska.playground.adapter.storage;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.kwolska.playground.domain.model.Account;
import pl.kwolska.playground.domain.model.Transfer;
import pl.kwolska.playground.domain.TransferRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// todo 3: to wyleci!
@Component
@Profile("inmemory")
class InMemoryTransferRepository implements TransferRepository {
  
  private final List<Transfer> transfers = new ArrayList<>();
  
  @Override
  public void addTransfer(Transfer transfer) {
    transfers.add(transfer);
  }
}
