package org.example.repository;

import org.example.entity.EmergencyCall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyCallRepository extends CrudRepository<EmergencyCall, Long> {
}
