package pl.kwolska.playground.adapter.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface JpaAccountRepository extends CrudRepository<AccountEntity, Integer> {
}
