package com.amb.mm.travel.backend;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}