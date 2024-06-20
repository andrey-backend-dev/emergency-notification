package org.example.dto.response;

import lombok.Getter;
import org.example.entity.EmergencyCalls;

import java.time.LocalDateTime;

@Getter
public class EmergencyCallsDTO {
    private long id;
    private long bindingId;
    private LocalDateTime dateCalled;
    private LocalDateTime dateReceived;

    public EmergencyCallsDTO(EmergencyCalls emergencyCalls) {
        this.id = emergencyCalls.getId();
        this.bindingId = emergencyCalls.getBinding().getId();
        this.dateCalled = emergencyCalls.getDateCalled();
        this.dateReceived = emergencyCalls.getDateReceived();
    }
}
