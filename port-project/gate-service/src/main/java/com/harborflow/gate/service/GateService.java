package com.harborflow.gate.service;

import com.harborflow.gate.client.ContainerServiceClient;
import com.harborflow.gate.model.GateEventType;
import com.harborflow.gate.model.GateLog;
import com.harborflow.gate.repository.GateLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GateService {

    private final GateLogRepository gateLogRepository;
    private final ContainerServiceClient containerServiceClient;

    public GateService(GateLogRepository gateLogRepository, ContainerServiceClient containerServiceClient) {
        this.gateLogRepository = gateLogRepository;
        this.containerServiceClient = containerServiceClient;
    }

    /**
     * Gate entry workflow: log the truck/container arrival, then trigger the
     * Container Service check-in workflow (which in turn allocates a yard slot).
     */
    public GateLog recordEntry(String vehicleNumber, String containerCode) {
        GateLog log = new GateLog();
        log.setVehicleNumber(vehicleNumber);
        log.setContainerCode(containerCode);
        log.setEventType(GateEventType.ENTRY);
        log.setEventTime(LocalDateTime.now());
        GateLog saved = gateLogRepository.save(log);

        containerServiceClient.checkIn(containerCode);
        return saved;
    }

    public GateLog recordExit(String vehicleNumber, String containerCode) {
        GateLog log = new GateLog();
        log.setVehicleNumber(vehicleNumber);
        log.setContainerCode(containerCode);
        log.setEventType(GateEventType.EXIT);
        log.setEventTime(LocalDateTime.now());
        return gateLogRepository.save(log);
    }

    public List<GateLog> history(String containerCode) {
        return gateLogRepository.findByContainerCode(containerCode);
    }
}
