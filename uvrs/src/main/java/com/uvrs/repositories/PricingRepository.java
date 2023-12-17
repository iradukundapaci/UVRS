package com.uvrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uvrs.models.Pricing;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Integer> {

}
