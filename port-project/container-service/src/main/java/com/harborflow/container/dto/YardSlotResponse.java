package com.harborflow.container.dto;

public class YardSlotResponse {
    private Long id;
    private String slotCode;
    private String block;
    private String status;
    private String containerId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSlotCode() { return slotCode; }
    public void setSlotCode(String slotCode) { this.slotCode = slotCode; }
    public String getBlock() { return block; }
    public void setBlock(String block) { this.block = block; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getContainerId() { return containerId; }
    public void setContainerId(String containerId) { this.containerId = containerId; }
}
