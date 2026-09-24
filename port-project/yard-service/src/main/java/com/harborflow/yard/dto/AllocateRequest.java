package com.harborflow.yard.dto;

import jakarta.validation.constraints.NotBlank;

public class AllocateRequest {
    @NotBlank
    private String containerId;

    public String getContainerId() { return containerId; }
    public void setContainerId(String containerId) { this.containerId = containerId; }
}
