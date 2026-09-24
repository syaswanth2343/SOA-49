package com.harborflow.container.repository;

import com.harborflow.container.model.Container;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContainerRepository extends JpaRepository<Container, Long> {
    Optional<Container> findByContainerCode(String containerCode);
}
