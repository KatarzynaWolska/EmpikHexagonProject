package pl.kwolska.playground.domain;

import pl.kwolska.playground.domain.model.Account;
import pl.kwolska.playground.domain.model.Transfer;

import java.util.List;

public interface TransferRepository {
  
  void addTransfer(Transfer transfer);
  
  List<Transfer> findAccountTransfers(Account account);
}