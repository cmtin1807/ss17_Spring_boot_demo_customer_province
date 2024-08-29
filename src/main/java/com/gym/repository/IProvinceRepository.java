package com.gym.repository;

import com.gym.model.DTO.NumberOfCustomer;
import com.gym.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProvinceRepository extends JpaRepository<Province, Long> {

    @Query(nativeQuery = true,value = "call NumberOfCustomerByProvince()")
    Iterable<NumberOfCustomer> getNumberOfCustomerByProvince();

}
