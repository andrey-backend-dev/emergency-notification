package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Caller2Receiver_Binding")
public class Caller2ReceiverBinding {
    @Id
    @SequenceGenerator(name = "binding_id_seq", sequenceName = "binding_id_seq", initialValue = 14, allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "binding_id_seq")
    private long id;
    private String receiver;
    private String service;
    private String serviceLink;
}
