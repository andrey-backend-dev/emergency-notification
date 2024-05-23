package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
public class Caller {
    @Id
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
