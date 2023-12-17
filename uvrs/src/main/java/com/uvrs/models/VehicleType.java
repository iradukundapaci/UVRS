package com.uvrs.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uvrs.enums.VehicleTypes;
import com.uvrs.enums.VehicleUnit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicle_types")
@Getter
@Setter
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int typeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleTypes vehicleType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_unit")
    private VehicleUnit vehicleUnit;

    @Column(name = "vehicle_capacity")
    private int vehicleCapacity;
    
    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private List<Vehicle> vehiclesByType;

}
