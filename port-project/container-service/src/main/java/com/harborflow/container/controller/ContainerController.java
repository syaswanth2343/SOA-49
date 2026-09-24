package com.harborflow.container.controller;

import com.harborflow.container.model.Container;
import com.harborflow.container.service.ContainerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/containers")
public class ContainerController {

    private final ContainerService containerService;

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @PostMapping
    public ResponseEntity<Container> register(@Valid @RequestBody Container container) {
        return ResponseEntity.status(HttpStatus.CREATED).body(containerService.register(container));
    }

    @GetMapping
    public ResponseEntity<List<Container>> getAll() {
        return ResponseEntity.ok(containerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Container> getById(@PathVariable Long id) {
        return ResponseEntity.ok(containerService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Container> update(@PathVariable Long id, @Valid @RequestBody Container container) {
        return ResponseEntity.ok(containerService.update(id, container));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        containerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkin/{containerCode}")
    public ResponseEntity<Container> checkIn(@PathVariable String containerCode) {
        return ResponseEntity.ok(containerService.checkIn(containerCode));
    }
}
