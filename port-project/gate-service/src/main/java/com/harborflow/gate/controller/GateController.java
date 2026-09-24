package com.harborflow.gate.controller;

import com.harborflow.gate.model.GateLog;
import com.harborflow.gate.service.GateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gate")
public class GateController {

    private final GateService gateService;

    public GateController(GateService gateService) {
        this.gateService = gateService;
    }

    @PostMapping("/entry")
    public ResponseEntity<GateLog> entry(@RequestParam String vehicleNumber, @RequestParam String containerCode) {
        return ResponseEntity.ok(gateService.recordEntry(vehicleNumber, containerCode));
    }

    @PostMapping("/exit")
    public ResponseEntity<GateLog> exit(@RequestParam String vehicleNumber, @RequestParam String containerCode) {
        return ResponseEntity.ok(gateService.recordExit(vehicleNumber, containerCode));
    }

    @GetMapping("/history/{containerCode}")
    public ResponseEntity<List<GateLog>> history(@PathVariable String containerCode) {
        return ResponseEntity.ok(gateService.history(containerCode));
    }
}
