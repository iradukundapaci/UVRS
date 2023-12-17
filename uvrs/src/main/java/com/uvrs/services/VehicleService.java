package com.uvrs.services;

import com.uvrs.enums.Pages;
import com.uvrs.enums.VehicleFilter;
import com.uvrs.models.Vehicle;
import com.uvrs.repositories.VehicleFilterQuery;
import com.uvrs.repositories.VehicleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

  @Autowired
  private VehicleRepository repo;

  @Autowired
  private VehicleFilterQuery vehicleFilterQuery;

  public Vehicle addVehicle(Vehicle vehicle) {
    try {
      Vehicle savedVehicle = repo.save(vehicle);
      return savedVehicle;
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  public boolean deleteVehicle(Vehicle vehicle) {
    try {
      repo.delete(vehicle);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  public Vehicle updateVehicle(Vehicle vehicle) {
    return addVehicle(vehicle);
  }

  public Page<Vehicle> retrieveAllVehicles(Pages page) {
    Page<Vehicle> vehicles = repo.findAll(
      PageRequest.of(page.getPage(), page.getPageSize())
    );

    if (vehicles != null) {
      return vehicles;
    }
    return null;
  }

  public Vehicle retrieveVehicleById(String plateNo) {
    Vehicle vehicle = repo.findById(plateNo).get();

    if (vehicle != null) {
      return vehicle;
    }
    return null;
  }

  public Page<Vehicle> filterVehicles(VehicleFilter filter) {
    Page<Vehicle> fillteredVehicles = vehicleFilterQuery.filterVehicles(filter);
    return fillteredVehicles;
  }

  public void saveGeneratedVehicles(List<Vehicle> generatedVehicles) {
    // Save each generated vehicle to the database
    for (Vehicle vehicle : generatedVehicles) {
      repo.save(vehicle);
    }
  }
}
