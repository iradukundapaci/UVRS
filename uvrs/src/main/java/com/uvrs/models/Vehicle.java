package com.uvrs.models;

import com.uvrs.enums.Brands;
import com.uvrs.enums.VehicleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
public class Vehicle {

  @Id
  @Column(name = "plate_number")
  private String plateNumber;

  @Enumerated(EnumType.STRING)
  @Column(name = "brand_name")
  private Brands brandName;

  @Column(name = "vehicle_model")
  private String vehicleModel;

  @Column(name = "year_of_realease")
  private int yearOfRealease;

  @Enumerated(EnumType.STRING)
  @Column(name = "vehicle_status")
  private VehicleStatus vehicleStatus;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = VehicleType.class)
  @JoinColumn(name = "type_id")
  private VehicleType type;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
  @JoinColumn(name = "vehicle_owner")
  private User vehicleOwner;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Pricing.class)
  @JoinColumn(name = "pricing_type")
  private Pricing pricingType;

  @OneToOne(
    fetch = FetchType.EAGER,
    targetEntity = Image.class,
    mappedBy = "vehicle"
  )
  private Image image;
}
