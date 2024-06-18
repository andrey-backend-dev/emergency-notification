package org.example.controller;

import org.example.dto.request.CallerCreateDto;
import org.example.dto.request.CallerUpdateDto;
import org.example.dto.response.CallerResponseDTO;
import org.example.service.CallerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/callers/")
public class CallerController {
    private final CallerService service;

    public CallerController(CallerService service) {
        this.service = service;
    }

    @PostMapping(value = "create/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CallerResponseDTO create(@RequestBody CallerCreateDto dto) {
        return service.create(dto);
    }

    @GetMapping(value = "{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public CallerResponseDTO findById(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @PatchMapping(value = "{id}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CallerResponseDTO updateById(@PathVariable("id") long id, @RequestBody CallerUpdateDto dto) {
        return service.updateById(id, dto);
    }

    @DeleteMapping(value = "{id}/")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Caller deleted.");
    }
}
