package pl.kwolska.playground.domain;

import pl.kwolska.playground.domain.model.Transfer;


public interface TransferRepository {
  
  void addTransfer(Transfer transfer);
}