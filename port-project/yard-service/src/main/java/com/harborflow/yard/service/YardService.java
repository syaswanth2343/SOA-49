package com.harborflow.yard.service;

import com.harborflow.yard.model.SlotStatus;
import com.harborflow.yard.model.YardSlot;
import com.harborflow.yard.repository.YardSlotRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Handles yard slot allocation and release. This is the core of the
 * "yard slot updates during loading and unloading" workflow.
 */
@Service
public class YardService {

    private final YardSlotRepository repository;

    public YardService(YardSlotRepository repository) {
        this.repository = repository;
    }

    public YardSlot createSlot(YardSlot slot) {
        return repository.save(slot);
    }

    public List<YardSlot> findAll() {
        return repository.findAll();
    }

    public List<YardSlot> findAvailable() {
        return repository.findByStatus(SlotStatus.EMPTY);
    }

    /** Allocates the first available slot to a container (simple first-fit strategy). */
    public synchronized YardSlot allocate(String containerId) {
        YardSlot slot = repository.findFirstByStatus(SlotStatus.EMPTY)
                .orElseThrow(() -> new IllegalStateException("No empty yard slot available"));
        slot.setStatus(SlotStatus.OCCUPIED);
        slot.setContainerId(containerId);
        return repository.save(slot);
    }

    /** Frees the slot occupied by the given container (used during loading onto a vessel). */
    public YardSlot release(String containerId) {
        YardSlot slot = repository.findByContainerId(containerId)
                .orElseThrow(() -> new EntityNotFoundException("No slot found for container " + containerId));
        slot.setStatus(SlotStatus.EMPTY);
        slot.setContainerId(null);
        return repository.save(slot);
    }
}
