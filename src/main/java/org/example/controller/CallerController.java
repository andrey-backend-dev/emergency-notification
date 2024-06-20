package org.example.controller;

import jakarta.validation.Valid;
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

    @GetMapping(value = "/id/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public CallerResponseDTO findById(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @GetMapping(value = "{username}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public CallerResponseDTO findByUsername(@PathVariable("username") String username) {
        return service.findByUsername(username);
    }

    @PatchMapping(value = "{username}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CallerResponseDTO updateByUsername(@PathVariable("username") String username, @RequestBody CallerUpdateDto dto) {
        return service.updateByUsername(username, dto);
    }

    @DeleteMapping(value = "{username}/")
    public ResponseEntity<?> deleteByUsername(@PathVariable("username") String username) {
        service.deleteByUsername(username);
        return ResponseEntity.ok("Caller deleted.");
    }
}
