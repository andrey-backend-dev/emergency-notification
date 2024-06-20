package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.util.enums.ServiceEnum;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Caller2Receiver_Binding")
public class Caller2ReceiverBinding {
    @Id
    @SequenceGenerator(name = "binding_id_seq", sequenceName = "binding_id_seq", initialValue = 14, allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "binding_id_seq")
    private long id;
    @ManyToOne
    @JoinColumn(name = "caller_id")
    private Caller caller;
    private String receiver;
    @Enumerated(EnumType.STRING)
    private ServiceEnum service;
    private String serviceLink;

    public Caller2ReceiverBinding(Caller caller, String receiver, ServiceEnum service, String serviceLink) {
        this.caller = caller;
        this.receiver = receiver;
        this.service = service;
        this.serviceLink = serviceLink;
    }
}
