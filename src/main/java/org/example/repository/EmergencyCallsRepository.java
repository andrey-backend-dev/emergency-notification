package org.example.repository;

import org.example.entity.EmergencyCalls;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyCallsRepository extends CrudRepository<EmergencyCalls, Long> {
}
