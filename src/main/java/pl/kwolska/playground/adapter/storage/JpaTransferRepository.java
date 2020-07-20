package pl.kwolska.playground.adapter.storage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTransferRepository extends JpaRepository<TransferEntity, Integer> {
}
