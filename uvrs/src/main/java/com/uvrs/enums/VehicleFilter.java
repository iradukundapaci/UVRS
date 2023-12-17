package com.uvrs.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleFilter {
    private VehicleStatus vehicleStatus;
    private Brands brand;
    private String model;
    private VehicleTypes vehicleTypes;
    private int yearDate;
    private VehicleUnit pricingUnit;
    private PricingMethod pricingMethod;
    private String sortDirection = "ASC";
    private String sortBy = "amount";
    private Pages page = new Pages();
}