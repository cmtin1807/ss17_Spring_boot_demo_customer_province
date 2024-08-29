package com.gym.repository;

import com.gym.model.Customer;
import com.gym.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findByFirstNameContaining(String firstName, Pageable pageable);
    Page<Customer> findAllByProvince(Province province,Pageable pageable);
}
