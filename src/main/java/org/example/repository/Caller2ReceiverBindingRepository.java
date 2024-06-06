package org.example.repository;

import org.example.entity.Caller2ReceiverBinding;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Caller2ReceiverBindingRepository extends CrudRepository<Caller2ReceiverBinding, Long> {
}
