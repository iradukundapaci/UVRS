package com.uvrs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uvrs.enums.Pages;
import com.uvrs.models.Pricing;
import com.uvrs.repositories.PricingRepository;

@Service
public class PricingService {

    @Autowired
    private PricingRepository repo;


    public boolean addPricing(Pricing pricing) {
        try {
            repo.save(pricing);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean deletePricing(int priceId) {
        try {
            repo.deleteById(priceId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean updatePricing(Pricing pricing) {
        return addPricing(pricing);
    }

    public List<Pricing> retrieveAllPricings() {
        return repo.findAll();
    }

    public Pricing retrievePricingById(int priceId) {
        return repo.findById(priceId).orElse(null);
    }

    public Page<Pricing> retrievePagedPricings(Pages pages) {
        return repo.findAll(PageRequest.of(pages.getPage(), pages.getPageSize()));
    }

    public void saveGeneratedPricings(List<Pricing> generatedPricings) {
        // Save each generated pricing to the database
        for (Pricing pricing : generatedPricings) {
            repo.save(pricing);
        }
    }

}
