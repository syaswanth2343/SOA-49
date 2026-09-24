package com.harborflow.yard.model;

import jakarta.persistence.*;

@Entity
@Table(name = "yard_slot")
public class YardSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String slotCode; // e.g. "BLOCK-A-12"

    private String block;

    @Enumerated(EnumType.STRING)
    private SlotStatus status = SlotStatus.EMPTY;

    private String containerId; // currently occupying container, if any

    public YardSlot() {}

    public YardSlot(String slotCode, String block) {
        this.slotCode = slotCode;
        this.block = block;
    }

    public Long getId() { return id; }
    public String getSlotCode() { return slotCode; }
    public void setSlotCode(String slotCode) { this.slotCode = slotCode; }
    public String getBlock() { return block; }
    public void setBlock(String block) { this.block = block; }
    public SlotStatus getStatus() { return status; }
    public void setStatus(SlotStatus status) { this.status = status; }
    public String getContainerId() { return containerId; }
    public void setContainerId(String containerId) { this.containerId = containerId; }
}
