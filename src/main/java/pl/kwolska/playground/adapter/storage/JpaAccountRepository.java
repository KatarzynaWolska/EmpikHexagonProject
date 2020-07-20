package pl.kwolska.playground.adapter.storage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, Integer> {
}
