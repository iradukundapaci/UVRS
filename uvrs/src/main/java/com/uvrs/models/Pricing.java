package com.uvrs.models;

import com.uvrs.enums.PricingMethod;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pricing")
@Getter
@Setter
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int priceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "pricing_method")
    private PricingMethod pricingMethod;

    @Column(name = "amount")
    private int amount;
}
