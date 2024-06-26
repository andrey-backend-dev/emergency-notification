package org.example.repository;

import org.example.entity.Receiver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverRepository extends CrudRepository<Receiver, Long> {
}
