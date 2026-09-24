package com.harborflow.carrier.repository;

import com.harborflow.carrier.model.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
}
