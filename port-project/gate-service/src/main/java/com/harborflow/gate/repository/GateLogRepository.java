package com.harborflow.gate.repository;

import com.harborflow.gate.model.GateLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GateLogRepository extends JpaRepository<GateLog, Long> {
    List<GateLog> findByContainerCode(String containerCode);
}
