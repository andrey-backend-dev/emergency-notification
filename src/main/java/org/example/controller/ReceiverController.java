package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.ReceiverCreateDTO;
import org.example.dto.request.ReceiverUpdateDTO;
import org.example.dto.response.ReceiverDTO;
import org.example.service.ReceiverService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/receivers/")
@RequiredArgsConstructor
public class ReceiverController {
    private final ReceiverService service;

    @PostMapping(path = "create/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReceiverDTO create(@RequestBody ReceiverCreateDTO dto) {
        return service.create(dto);
    }

    @PatchMapping(path = "update/{id}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReceiverDTO updateById(@PathVariable("id") long id, @RequestBody ReceiverUpdateDTO dto) throws AccessDeniedException {
        return service.updateById(id, dto);
    }
}
