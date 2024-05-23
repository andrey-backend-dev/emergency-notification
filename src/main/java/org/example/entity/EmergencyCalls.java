package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Emergency_Calls")
public class EmergencyCalls {
    @Id
    private long id;
    @ManyToOne
    private Caller2ReceiverBinding binding;
    private LocalDateTime dateCalled;
    private LocalDateTime dateReceived;
}
