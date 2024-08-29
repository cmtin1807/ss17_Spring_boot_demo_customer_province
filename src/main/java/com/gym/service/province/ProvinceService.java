package com.gym.service.province;

import com.gym.model.DTO.NumberOfCustomer;
import com.gym.model.Province;
import com.gym.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceService implements IProvinceService {
    @Autowired
    private IProvinceRepository provinceRepository;

    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Optional<Province> findById(Long id) {
        return provinceRepository.findById(id);
    }

    @Override
    public void save(Province province) {
provinceRepository.save(province);
    }

    @Override
    public void deleteById(Long id) {
provinceRepository.deleteById(id);
    }

    @Override
    public Iterable<NumberOfCustomer> getNumberOfCustomerByProvince() {
        return provinceRepository.getNumberOfCustomerByProvince();
    }
}
