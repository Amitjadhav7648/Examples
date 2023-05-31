package com.cg.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.pojo.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	boolean existsByMobileNumber(String mobileNumber);

	boolean existsByEmail(String email);

}
