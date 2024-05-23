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
    private long id;
    private String receiver;
    private String service;
    private String serviceLink;
}
