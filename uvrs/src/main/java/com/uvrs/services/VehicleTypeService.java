package com.uvrs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uvrs.enums.Pages;
import com.uvrs.models.VehicleType;
import com.uvrs.repositories.VehicleTypeRepository;

@Service
public class VehicleTypeService {

    @Autowired
    private VehicleTypeRepository repo;

    public void saveGeneratedVehicleTypes(List<VehicleType> generatedVehicleTypes) {
        // Save each generated vehicle to the database
        for (VehicleType vehicleType : generatedVehicleTypes) {
            repo.save(vehicleType);
        }
    }

    public boolean addVehicleType(VehicleType vehicleType) {
        try {
            repo.save(vehicleType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean deleteVehicleType(int typeId) {
        try {
            repo.deleteById(typeId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean updateVehicleType(VehicleType vehicleType) {
        return addVehicleType(vehicleType);
    }

    public List<VehicleType> retrieveAllVehicleTypes() {
        return repo.findAll();
    }

    public VehicleType retrieveVehicleTypeById(int typeId) {
        return repo.findById(typeId).orElse(null);
    }

    public Page<VehicleType> retrievePagedVehicleTypes(Pages pages) {
        return repo.findAll(PageRequest.of(pages.getPage(), pages.getPageSize()));
    }
}
