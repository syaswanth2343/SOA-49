package com.harborflow.container.service;

import com.harborflow.container.client.YardServiceClient;
import com.harborflow.container.dto.AllocateRequest;
import com.harborflow.container.dto.YardSlotResponse;
import com.harborflow.container.model.Container;
import com.harborflow.container.model.ContainerStatus;
import com.harborflow.container.repository.ContainerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerService {

    private final ContainerRepository containerRepository;
    private final YardServiceClient yardServiceClient;

    public ContainerService(ContainerRepository containerRepository, YardServiceClient yardServiceClient) {
        this.containerRepository = containerRepository;
        this.yardServiceClient = yardServiceClient;
    }

    public Container register(Container container) {
        container.setStatus(ContainerStatus.REGISTERED);
        return containerRepository.save(container);
    }

    public List<Container> findAll() {
        return containerRepository.findAll();
    }

    public Container findById(Long id) {
        return containerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Container not found: " + id));
    }

    public Container update(Long id, Container updated) {
        Container existing = findById(id);
        existing.setContainerType(updated.getContainerType());
        existing.setCarrierId(updated.getCarrierId());
        return containerRepository.save(existing);
    }

    public void delete(Long id) {
        containerRepository.delete(findById(id));
    }

    /**
     * Container check-in workflow:
     * 1. Gate Service calls this after scanning the container at the gate.
     * 2. Container status is moved to CHECKED_IN.
     * 3. Yard Service is called (via Feign + Eureka + load balancer) to allocate a free slot.
     * 4. Container is updated with its assigned yard slot and moved to IN_YARD.
     */
    public Container checkIn(String containerCode) {
        Container container = containerRepository.findByContainerCode(containerCode)
                .orElseThrow(() -> new EntityNotFoundException("Unknown container: " + containerCode));

        container.setStatus(ContainerStatus.CHECKED_IN);
        containerRepository.save(container);

        YardSlotResponse slot = yardServiceClient.allocateSlot(new AllocateRequest(containerCode));

        container.setCurrentYardSlot(slot.getSlotCode());
        container.setStatus(ContainerStatus.IN_YARD);
        return containerRepository.save(container);
    }
}
