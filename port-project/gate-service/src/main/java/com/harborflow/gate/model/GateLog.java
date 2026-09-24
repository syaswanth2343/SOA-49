package com.harborflow.gate.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "gate_log")
public class GateLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleNumber;
    private String containerCode;

    @Enumerated(EnumType.STRING)
    private GateEventType eventType; // ENTRY or EXIT

    private LocalDateTime eventTime;

    public GateLog() {}

    public Long getId() { return id; }
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    public String getContainerCode() { return containerCode; }
    public void setContainerCode(String containerCode) { this.containerCode = containerCode; }
    public GateEventType getEventType() { return eventType; }
    public void setEventType(GateEventType eventType) { this.eventType = eventType; }
    public LocalDateTime getEventTime() { return eventTime; }
    public void setEventTime(LocalDateTime eventTime) { this.eventTime = eventTime; }
}
