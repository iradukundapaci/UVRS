package com.uvrs.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.uvrs.enums.VehicleFilter;
import com.uvrs.models.Pricing;
import com.uvrs.models.Vehicle;
import com.uvrs.models.VehicleType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class VehicleFilterQuery {
    @Autowired
    private EntityManagerFactory factory;

    public Page<Vehicle> filterVehicles(VehicleFilter filter){

        EntityManager entityManager = factory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vehicle> criteriaQuery = criteriaBuilder.createQuery(Vehicle.class);


        // select * from vehicles
        Root<Vehicle> root = criteriaQuery.from(Vehicle.class);

        Predicate predicate = getPredicate(filter, root, criteriaBuilder);
        criteriaQuery.where(predicate);
        setOrder(filter, root, criteriaBuilder, criteriaQuery);

        TypedQuery<Vehicle> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(filter.getPage().getPage()* filter.getPage().getPageSize());
        typedQuery.setMaxResults(filter.getPage().getPageSize());
        List<Vehicle> resultList = typedQuery.getResultList();

        Long totalCount = getVehicleCount(filter, criteriaBuilder, entityManager);

        Pageable pageable = PageRequest.of(filter.getPage().getPage(), filter.getPage().getPageSize());
        return new PageImpl<>(resultList, pageable, totalCount);
    }


    private Long getVehicleCount(VehicleFilter filter, CriteriaBuilder criteriaBuilder, EntityManager entityManager) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Vehicle> countRoot = countQuery.from(Vehicle.class);
        Predicate predicate = getPredicate(filter, countRoot, criteriaBuilder);
        countQuery.select(criteriaBuilder.count(countRoot));
        countQuery.where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    public Predicate getPredicate(VehicleFilter filter,
                                Root<Vehicle> root,
                                CriteriaBuilder criteriaBuilder
    ) {
        List<Predicate> predicates = new ArrayList<>();

        //prepare where clause
        if(Objects.nonNull(filter.getVehicleStatus())){
            Predicate statusPredct = criteriaBuilder.equal(root.get("vehicleStatus"), filter.getVehicleStatus());
            predicates.add(statusPredct);
        }

        if(Objects.nonNull(filter.getBrand())){
            Predicate brandPredct = criteriaBuilder.equal(root.get("brandName"), filter.getBrand());
            predicates.add(brandPredct);
        }

        if(Objects.nonNull(filter.getModel())){
            Predicate modelPredct = criteriaBuilder.equal(root.get("vehicleModel"), filter.getModel());
            predicates.add(modelPredct);
        }

        if(Objects.nonNull(filter.getVehicleTypes())){
            Join<Vehicle, VehicleType> typeJoin = root.join("type", JoinType.INNER);
            Predicate typePredct = criteriaBuilder.equal(typeJoin.get("vehicleType"), filter.getVehicleTypes());
            predicates.add(typePredct);
        }

        if(Objects.nonNull(filter.getPricingUnit())){
            Join<Vehicle, VehicleType> typeJoin = root.join("type");
            Predicate priceUnitPredct = criteriaBuilder.equal(typeJoin.get("vehicleUnit"), filter.getPricingUnit());
            predicates.add(priceUnitPredct);
        }

        if(Objects.nonNull(filter.getPricingMethod())){
            Join<Vehicle, Pricing> typeJoin = root.join("pricingType");
            Predicate pricingMethodPredct = criteriaBuilder.equal(typeJoin.get("pricingMethod"), filter.getPricingMethod());
            predicates.add(pricingMethodPredct);
        }

        //concat the querry
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(VehicleFilter filter,
                        Root<Vehicle> root,
                        CriteriaBuilder criteriaBuilder,
                        CriteriaQuery<Vehicle> criteriaQuery
    ){
        if (Sort.Direction.valueOf(filter.getSortDirection()).equals(Sort.Direction.ASC)){
            Join<Vehicle, Pricing> typeJoin = root.join("pricingType");
            criteriaQuery.orderBy(criteriaBuilder.asc(typeJoin.get(filter.getSortBy())));

        }else{
            Join<Vehicle, Pricing> typeJoin = root.join("pricingType");
            criteriaQuery.orderBy(criteriaBuilder.desc(typeJoin.get(filter.getSortBy())));
        }
     }

}
