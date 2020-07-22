package pl.kwolska.playground.adapter.storage;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaTransferRepository extends CrudRepository<TransferEntity, Integer> {

  List<TransferEntity> findByDebitAccountId(int debitAccountId);
  List<TransferEntity> findByCreditAccountId(int creditAccountId);

}
