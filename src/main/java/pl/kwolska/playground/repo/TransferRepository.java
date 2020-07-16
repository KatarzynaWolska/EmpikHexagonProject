package pl.kwolska.playground.repo;

import org.springframework.stereotype.Component;
import pl.kwolska.playground.domain.Account;
import pl.kwolska.playground.domain.Transfer;

import java.util.List;

public interface TransferRepository {
  
  void addTransfer(Transfer transfer);
  
  List<Transfer> getTransfers();
  
  Transfer getTransferById(int transferId);
  
  void deleteTransfer(int transferId);
  
  List<Transfer> getAccountTransfers(Account account);
}