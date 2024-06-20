package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Emergency_Calls")
public class EmergencyCalls {
    @Id
    @SequenceGenerator(name = "emergency_id_seq", sequenceName = "emergency_id_seq", initialValue = 7, allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emergency_id_seq")
    private long id;
    @ManyToOne
    private Caller2ReceiverBinding binding;
    private LocalDateTime dateCalled;
    private LocalDateTime dateReceived;

    public EmergencyCalls(Caller2ReceiverBinding binding, LocalDateTime dateCalled) {
        this.binding = binding;
        this.dateCalled = dateCalled;
    }
}
