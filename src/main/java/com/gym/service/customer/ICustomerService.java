package com.gym.service.customer;

import com.gym.model.Customer;
import com.gym.model.Province;
import com.gym.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends IGenerateService<Customer> {
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findByFirstNameContaining(String firstName, Pageable pageable);
    Page<Customer> findAllByProvince(Province province,Pageable pageable);

}

