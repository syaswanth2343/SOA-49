package com.harborflow.container.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "container")
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String containerCode; // ISO 6346 container id, e.g. MSCU1234567

    private String containerType; // e.g. 20FT, 40FT, REEFER

    @NotNull
    private Long carrierId;

    @Enumerated(EnumType.STRING)
    private ContainerStatus status = ContainerStatus.REGISTERED;

    private String currentYardSlot;

    public Container() {}

    public Long getId() { return id; }
    public String getContainerCode() { return containerCode; }
    public void setContainerCode(String containerCode) { this.containerCode = containerCode; }
    public String getContainerType() { return containerType; }
    public void setContainerType(String containerType) { this.containerType = containerType; }
    public Long getCarrierId() { return carrierId; }
    public void setCarrierId(Long carrierId) { this.carrierId = carrierId; }
    public ContainerStatus getStatus() { return status; }
    public void setStatus(ContainerStatus status) { this.status = status; }
    public String getCurrentYardSlot() { return currentYardSlot; }
    public void setCurrentYardSlot(String currentYardSlot) { this.currentYardSlot = currentYardSlot; }
}
