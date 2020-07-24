package pl.kwolska.playground.adapter.storage;

import org.springframework.data.repository.CrudRepository;

interface JpaAccountRepository extends CrudRepository<AccountEntity, Integer> {
}
