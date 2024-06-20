package org.example.controller;

import org.example.dto.request.Caller2ReceiverBindingCreateDTO;
import org.example.dto.response.Caller2ReceiverBindingDTO;
import org.example.service.Caller2ReceiverBindingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bindings/")
public class Caller2ReceiverBindingController {
    private final Caller2ReceiverBindingService service;

    Caller2ReceiverBindingController(Caller2ReceiverBindingService service) {
        this.service = service;
    }

    @PostMapping(path = "/create/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Caller2ReceiverBindingDTO create(@RequestBody Caller2ReceiverBindingCreateDTO dto) {
        return service.create(dto);
    }
}
