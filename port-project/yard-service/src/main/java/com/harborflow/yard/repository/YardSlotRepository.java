package com.harborflow.yard.repository;

import com.harborflow.yard.model.SlotStatus;
import com.harborflow.yard.model.YardSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface YardSlotRepository extends JpaRepository<YardSlot, Long> {
    List<YardSlot> findByStatus(SlotStatus status);
    Optional<YardSlot> findFirstByStatus(SlotStatus status);
    Optional<YardSlot> findByContainerId(String containerId);
}
