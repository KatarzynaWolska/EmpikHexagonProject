package pl.kwolska.playground.repo;

import org.springframework.stereotype.Component;
import pl.kwolska.playground.domain.Account;
import pl.kwolska.playground.domain.Transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TransferRepositoryAdapter implements TransferRepository {
  
  private final List<Transfer> transfers = new ArrayList<>();
  
  @Override
  public void addTransfer(Transfer transfer) {
    transfers.add(transfer);
  }
  
  @Override
  public List<Transfer> getTransfers() {
    return transfers;
  }
  
  @Override
  public Transfer getTransferById(int transferId) {
    Optional<Transfer> transfer = transfers.stream().filter(t -> t.getId() == transferId).findFirst();
    return transfer.orElse(null);
  }
  
  @Override
  public void deleteTransfer(int transferId) {
    transfers.removeIf(transfer -> transfer.getId() == transferId);
  }
  
  @Override
  public List<Transfer> getAccountTransfers(Account account) {
    return transfers.stream()
        .filter(t -> t.getCredit().equals(account))
        .collect(Collectors.toList());
  }
}
