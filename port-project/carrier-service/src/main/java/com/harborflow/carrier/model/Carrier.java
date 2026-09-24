package com.harborflow.carrier.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "carrier")
public class Carrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String carrierName;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String carrierCode; // e.g. shipping-line code

    private String vesselName;
    private String vesselImoNumber;
    private String contactEmail;

    public Carrier() {}

    public Long getId() { return id; }
    public String getCarrierName() { return carrierName; }
    public void setCarrierName(String carrierName) { this.carrierName = carrierName; }
    public String getCarrierCode() { return carrierCode; }
    public void setCarrierCode(String carrierCode) { this.carrierCode = carrierCode; }
    public String getVesselName() { return vesselName; }
    public void setVesselName(String vesselName) { this.vesselName = vesselName; }
    public String getVesselImoNumber() { return vesselImoNumber; }
    public void setVesselImoNumber(String vesselImoNumber) { this.vesselImoNumber = vesselImoNumber; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
}
