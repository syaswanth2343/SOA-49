package com.harborflow.carrier.controller;

import com.harborflow.carrier.model.Carrier;
import com.harborflow.carrier.service.CarrierService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * All requests reach this controller only after the API Gateway has validated
 * the caller's JWT and forwarded X-Auth-User / X-Auth-Role headers.
 */
@RestController
@RequestMapping("/api/carriers")
public class CarrierController {

    private final CarrierService carrierService;

    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @PostMapping
    public ResponseEntity<Carrier> create(@Valid @RequestBody Carrier carrier) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carrierService.create(carrier));
    }

    @GetMapping
    public ResponseEntity<List<Carrier>> getAll() {
        return ResponseEntity.ok(carrierService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrier> getById(@PathVariable Long id) {
        return ResponseEntity.ok(carrierService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrier> update(@PathVariable Long id, @Valid @RequestBody Carrier carrier) {
        return ResponseEntity.ok(carrierService.update(id, carrier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carrierService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
