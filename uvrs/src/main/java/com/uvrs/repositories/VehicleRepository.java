package com.uvrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uvrs.models.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String>{
}
