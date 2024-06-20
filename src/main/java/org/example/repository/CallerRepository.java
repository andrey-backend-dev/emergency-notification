package org.example.repository;

import org.example.entity.Caller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CallerRepository extends CrudRepository<Caller, Long> {
    Optional<Caller> findByUsername(String username);
    void deleteByUsername(String username);
}
