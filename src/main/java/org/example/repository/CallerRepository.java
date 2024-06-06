package org.example.repository;

import org.example.entity.Caller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallerRepository extends CrudRepository<Caller, Long> {
}
