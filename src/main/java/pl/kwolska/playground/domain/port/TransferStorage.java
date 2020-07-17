package pl.kwolska.playground.domain.port;

import pl.kwolska.playground.domain.model.Account;
import pl.kwolska.playground.domain.model.Transfer;

import java.util.List;

public interface TransferStorage {
  
  void addTransfer(Transfer transfer);
  
  List<Transfer> findAccountTransfers(Account account);
}