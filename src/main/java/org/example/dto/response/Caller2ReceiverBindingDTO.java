package org.example.dto.response;


import lombok.Getter;
import org.example.entity.Caller2ReceiverBinding;
import org.example.util.enums.ServiceEnum;

@Getter
public class Caller2ReceiverBindingDTO {
    private long callerId;
    private String receiver;
    private ServiceEnum service;
    private String serviceLink;

    public Caller2ReceiverBindingDTO(Caller2ReceiverBinding binding) {
        this.callerId = binding.getCaller().getId();
        this.receiver = binding.getReceiver();
        this.service = binding.getService();
        this.serviceLink = binding.getServiceLink();
    }
}
