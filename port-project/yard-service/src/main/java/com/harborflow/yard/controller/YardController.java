package com.harborflow.yard.controller;

import com.harborflow.yard.dto.AllocateRequest;
import com.harborflow.yard.model.YardSlot;
import com.harborflow.yard.service.YardService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/yard")
public class YardController {

    private final YardService yardService;

    public YardController(YardService yardService) {
        this.yardService = yardService;
    }

    @PostMapping("/slots")
    public ResponseEntity<YardSlot> createSlot(@RequestBody YardSlot slot) {
        return ResponseEntity.ok(yardService.createSlot(slot));
    }

    @GetMapping("/slots")
    public ResponseEntity<List<YardSlot>> allSlots() {
        return ResponseEntity.ok(yardService.findAll());
    }

    @GetMapping("/slots/available")
    public ResponseEntity<List<YardSlot>> availableSlots() {
        return ResponseEntity.ok(yardService.findAvailable());
    }

    @PostMapping("/allocate")
    public ResponseEntity<YardSlot> allocate(@Valid @RequestBody AllocateRequest request) {
        return ResponseEntity.ok(yardService.allocate(request.getContainerId()));
    }

    @PostMapping("/release/{containerId}")
    public ResponseEntity<YardSlot> release(@PathVariable String containerId) {
        return ResponseEntity.ok(yardService.release(containerId));
    }
}
