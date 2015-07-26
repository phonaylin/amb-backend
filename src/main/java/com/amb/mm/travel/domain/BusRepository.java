package com.amb.mm.travel.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BusRepository extends PagingAndSortingRepository<Bus, Long> {

    List<Bus> findByOperatorName(String operatorName);
    
}