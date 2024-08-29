package com.gym.service.province;

import com.gym.model.DTO.NumberOfCustomer;
import com.gym.model.Province;
import com.gym.service.IGenerateService;

public interface IProvinceService extends IGenerateService<Province> {
    Iterable<NumberOfCustomer> getNumberOfCustomerByProvince();
}
