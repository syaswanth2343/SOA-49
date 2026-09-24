package com.harborflow.yard.service;

import com.harborflow.yard.model.SlotStatus;
import com.harborflow.yard.model.YardSlot;
import com.harborflow.yard.repository.YardSlotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/** Unit tests covering the core yard slot allocation / release workflow. */
@ExtendWith(MockitoExtension.class)
class YardServiceTest {

    @Mock
    private YardSlotRepository repository;

    @InjectMocks
    private YardService yardService;

    @Test
    void allocate_assignsFirstEmptySlotToContainer() {
        YardSlot slot = new YardSlot("BLOCK-A-01", "A");
        slot.setStatus(SlotStatus.EMPTY);
        when(repository.findFirstByStatus(SlotStatus.EMPTY)).thenReturn(Optional.of(slot));
        when(repository.save(any(YardSlot.class))).thenAnswer(inv -> inv.getArgument(0));

        YardSlot result = yardService.allocate("MSCU1234567");

        assertThat(result.getStatus()).isEqualTo(SlotStatus.OCCUPIED);
        assertThat(result.getContainerId()).isEqualTo("MSCU1234567");
    }

    @Test
    void allocate_whenNoSlotsAvailable_throwsException() {
        when(repository.findFirstByStatus(SlotStatus.EMPTY)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> yardService.allocate("MSCU1234567"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("No empty yard slot");
    }

    @Test
    void release_freesSlotOccupiedByContainer() {
        YardSlot slot = new YardSlot("BLOCK-A-01", "A");
        slot.setStatus(SlotStatus.OCCUPIED);
        slot.setContainerId("MSCU1234567");
        when(repository.findByContainerId("MSCU1234567")).thenReturn(Optional.of(slot));
        when(repository.save(any(YardSlot.class))).thenAnswer(inv -> inv.getArgument(0));

        YardSlot result = yardService.release("MSCU1234567");

        assertThat(result.getStatus()).isEqualTo(SlotStatus.EMPTY);
        assertThat(result.getContainerId()).isNull();
    }
}
