package com.harborflow.container.dto;

public class AllocateRequest {
    private String containerId;
    public AllocateRequest() {}
    public AllocateRequest(String containerId) { this.containerId = containerId; }
    public String getContainerId() { return containerId; }
    public void setContainerId(String containerId) { this.containerId = containerId; }
}
