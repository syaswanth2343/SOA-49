package com.harborflow.carrier.service;

import com.harborflow.carrier.model.Carrier;
import com.harborflow.carrier.repository.CarrierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/** Unit tests for CarrierService business logic, using Mockito to isolate the repository layer. */
@ExtendWith(MockitoExtension.class)
class CarrierServiceTest {

    @Mock
    private CarrierRepository carrierRepository;

    @InjectMocks
    private CarrierService carrierService;

    @Test
    void create_savesAndReturnsCarrier() {
        Carrier carrier = new Carrier();
        carrier.setCarrierName("Maersk");
        carrier.setCarrierCode("MAEU");
        when(carrierRepository.save(carrier)).thenReturn(carrier);

        Carrier result = carrierService.create(carrier);

        assertThat(result.getCarrierName()).isEqualTo("Maersk");
        verify(carrierRepository, times(1)).save(carrier);
    }

    @Test
    void findById_whenNotFound_throwsException() {
        when(carrierRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> carrierService.findById(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void findAll_returnsAllCarriers() {
        Carrier c1 = new Carrier();
        c1.setCarrierName("Maersk");
        Carrier c2 = new Carrier();
        c2.setCarrierName("MSC");
        when(carrierRepository.findAll()).thenReturn(List.of(c1, c2));

        List<Carrier> result = carrierService.findAll();

        assertThat(result).hasSize(2);
    }

    @Test
    void delete_removesExistingCarrier() {
        Carrier carrier = new Carrier();
        carrier.setCarrierName("Maersk");
        when(carrierRepository.findById(1L)).thenReturn(Optional.of(carrier));

        carrierService.delete(1L);

        verify(carrierRepository, times(1)).delete(carrier);
    }
}
