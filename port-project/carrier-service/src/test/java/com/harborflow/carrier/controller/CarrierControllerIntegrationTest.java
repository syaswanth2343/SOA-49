package com.harborflow.carrier.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harborflow.carrier.model.Carrier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test that boots the full Spring context (with an in-memory H2
 * database) and exercises the CRUD REST API end to end via MockMvc.
 */
@SpringBootTest
@AutoConfigureMockMvc
class CarrierControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAndFetchCarrier_endToEnd() throws Exception {
        Carrier carrier = new Carrier();
        carrier.setCarrierName("CMA CGM");
        carrier.setCarrierCode("CMAU");
        carrier.setVesselName("CMA CGM Marco Polo");

        String response = mockMvc.perform(post("/api/carriers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carrier)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.carrierName").value("CMA CGM"))
                .andReturn().getResponse().getContentAsString();

        Carrier created = objectMapper.readValue(response, Carrier.class);

        mockMvc.perform(get("/api/carriers/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carrierCode").value("CMAU"));
    }

    @Test
    void getAllCarriers_returnsList() throws Exception {
        mockMvc.perform(get("/api/carriers"))
                .andExpect(status().isOk());
    }
}
