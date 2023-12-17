package com.uvrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.uvrs.enums.Pages;
import com.uvrs.models.Pricing;
import com.uvrs.services.PricingService;

import java.util.List;

@RestController
@RequestMapping("/api/pricings")
public class PricingController {

    @Autowired
    private final PricingService pricingService;

    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @PostMapping("/add")
    public boolean addPricing(@RequestBody Pricing pricing) {
        return pricingService.addPricing(pricing);
    }

    @DeleteMapping("/delete/{priceId}")
    public boolean deletePricing(@PathVariable int priceId) {
        return pricingService.deletePricing(priceId);
    }

    @PutMapping("/update")
    public boolean updatePricing(@RequestBody Pricing pricing) {
        return pricingService.updatePricing(pricing);
    }

    @GetMapping("/all")
    public List<Pricing> retrieveAllPricings() {
        return pricingService.retrieveAllPricings();
    }

    @GetMapping("/{priceId}")
    public Pricing retrievePricingById(@PathVariable int priceId) {
        return pricingService.retrievePricingById(priceId);
    }

    @GetMapping("/paged")
    public Page<Pricing> retrievePagedPricings(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int pageSize) {
        Pages pages = new Pages();
        pages.setPage(page);
        pages.setPageSize(pageSize);
        return pricingService.retrievePagedPricings(pages);
    }

    @PostMapping("/saveGenerated")
    public void saveGeneratedPricings(@RequestBody List<Pricing> generatedPricings) {
        pricingService.saveGeneratedPricings(generatedPricings);
    }
}

