package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Caller {
    @Id
    @SequenceGenerator(name = "caller_id_seq", sequenceName = "caller_id_seq", initialValue = 5, allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caller_id_seq")
    private long id;
    private String username;
    private String password;
    private String email;
    private String message;
    private String image;
    @OneToMany
    @JoinColumn(name = "caller_id")
    private List<Caller2ReceiverBinding> bindings;
}
