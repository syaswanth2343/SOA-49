package com.harborflow.carrier.service;

import com.harborflow.carrier.model.Carrier;
import com.harborflow.carrier.repository.CarrierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrierService {

    private final CarrierRepository carrierRepository;

    public CarrierService(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }

    public Carrier create(Carrier carrier) {
        return carrierRepository.save(carrier);
    }

    public List<Carrier> findAll() {
        return carrierRepository.findAll();
    }

    public Carrier findById(Long id) {
        return carrierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carrier not found: " + id));
    }

    public Carrier update(Long id, Carrier updated) {
        Carrier existing = findById(id);
        existing.setCarrierName(updated.getCarrierName());
        existing.setCarrierCode(updated.getCarrierCode());
        existing.setVesselName(updated.getVesselName());
        existing.setVesselImoNumber(updated.getVesselImoNumber());
        existing.setContactEmail(updated.getContactEmail());
        return carrierRepository.save(existing);
    }

    public void delete(Long id) {
        carrierRepository.delete(findById(id));
    }
}
