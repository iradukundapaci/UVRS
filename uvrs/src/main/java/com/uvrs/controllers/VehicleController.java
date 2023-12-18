package com.uvrs.controllers;

import com.uvrs.enums.Pages;
import com.uvrs.enums.VehicleFilter;
import com.uvrs.models.Vehicle;
import com.uvrs.services.VehicleService;
import jakarta.websocket.server.PathParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {

  @Autowired
  private VehicleService vehicleService;

  @PostMapping("/add")
  public boolean addVehicle(@RequestBody Vehicle vehicle) {
    Vehicle savedVehicle = vehicleService.addVehicle(vehicle);
    if (savedVehicle != null) {
      return true;
    }
    return false;
  }

  @DeleteMapping("/delete")
  public boolean deleteVehicle(@RequestBody Vehicle vehicle) {
    return vehicleService.deleteVehicle(vehicle);
  }

  @PutMapping("/update")
  public boolean updateVehicle(@RequestBody Vehicle vehicle) {
    Vehicle updatVehicle = vehicleService.updateVehicle(vehicle);
    if (updatVehicle != null) {
      return true;
    }
    return false;
  }

  @GetMapping("/all")
  public Page<Vehicle> retrieveAllVehicles(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int pageSize
  ) {
    Pages pages = new Pages();
    pages.setPage(page);
    pages.setPageSize(pageSize);
    return vehicleService.retrieveAllVehicles(pages);
  }

  @GetMapping("/id{plateNo}")
  public Vehicle retrieveVehicleById(@PathVariable String plateNo) {
    return vehicleService.retrieveVehicleById(plateNo);
  }

  @PostMapping("/filter")
  public ResponseEntity<?> filterVehicles(@RequestBody VehicleFilter filter) {
    try {
      Page<Vehicle> filteredVehicles = vehicleService.filterVehicles(filter);
      return new ResponseEntity<>(filteredVehicles, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

  @PostMapping("/saveGenerated")
  public void saveGeneratedVehicles(
    @RequestBody List<Vehicle> generatedVehicles
  ) {
    vehicleService.saveGeneratedVehicles(generatedVehicles);
  }
}
