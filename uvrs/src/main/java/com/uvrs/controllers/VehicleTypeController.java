package com.uvrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.uvrs.enums.Pages;
import com.uvrs.models.VehicleType;
import com.uvrs.services.VehicleTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-types")
public class VehicleTypeController {

    @Autowired
    private final VehicleTypeService vehicleTypeService;

    public VehicleTypeController(VehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    @PostMapping("/add")
    public boolean addVehicleType(@RequestBody VehicleType vehicleType) {
        return vehicleTypeService.addVehicleType(vehicleType);
    }

    @DeleteMapping("/delete/{typeId}")
    public boolean deleteVehicleType(@PathVariable int typeId) {
        return vehicleTypeService.deleteVehicleType(typeId);
    }

    @PutMapping("/update")
    public boolean updateVehicleType(@RequestBody VehicleType vehicleType) {
        return vehicleTypeService.updateVehicleType(vehicleType);
    }

    @GetMapping("/all")
    public List<VehicleType> retrieveAllVehicleTypes() {
        return vehicleTypeService.retrieveAllVehicleTypes();
    }

    @GetMapping("/{typeId}")
    public VehicleType retrieveVehicleTypeById(@PathVariable int typeId) {
        return vehicleTypeService.retrieveVehicleTypeById(typeId);
    }

    @GetMapping("/paged")
    public Page<VehicleType> retrievePagedVehicleTypes(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int pageSize) {
        Pages pages = new Pages();
        pages.setPage(page);
        pages.setPageSize(pageSize);
        return vehicleTypeService.retrievePagedVehicleTypes(pages);
    }

    @PostMapping("/saveGenerated")
    public void saveGeneratedVehicleTypes(@RequestBody List<VehicleType> generatedVehicleTypes) {
        vehicleTypeService.saveGeneratedVehicleTypes(generatedVehicleTypes);
    }
}
