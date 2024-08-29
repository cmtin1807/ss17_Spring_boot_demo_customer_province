package com.gym.controller;


import com.gym.model.Customer;
import com.gym.model.Province;
import com.gym.service.customer.ICustomerService;
import com.gym.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("provinces")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public ModelAndView getProvinces() {
        ModelAndView mav = new ModelAndView("province/list");
        mav.addObject("provinces", provinceService.findAll());
        mav.addObject("provinceCount",provinceService.getNumberOfCustomerByProvince());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createProvince() {
        ModelAndView mav = new ModelAndView("province/create");
        mav.addObject("province", new Province());
        return mav;
    }
    @PostMapping("/create")
    public ModelAndView createProvince(@ModelAttribute("province") Province province) {
        provinceService.save(province);
        ModelAndView mav = new ModelAndView("redirect:/provinces");
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editProvince(@PathVariable Long id) {
        Optional<Province> province = provinceService.findById(id);
        if (province.isPresent()) {
            ModelAndView mav = new ModelAndView("province/update");
            mav.addObject("province", province.get());
            return mav;
        }
        return new ModelAndView("error_404");
    }
    @PostMapping("/update")
    public ModelAndView updateProvince(@ModelAttribute("province") Province province) {
        provinceService.save(province);
        ModelAndView mav = new ModelAndView("redirect:/provinces");
        return mav;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProvince(@PathVariable Long id) {
        Optional<Province> province = provinceService.findById(id);
        if (province.isPresent()) {
            ModelAndView mav = new ModelAndView("province/delete");
            mav.addObject("province", province.get());
            return mav;
        }
        return new ModelAndView("error_404");
    }

    @PostMapping("/delete")
    public ModelAndView deleteProvince(@ModelAttribute("province") Province province) {
        provinceService.deleteById(province.getId());
        ModelAndView mav = new ModelAndView("redirect:/provinces");
        return mav;
    }

    @GetMapping("detail/{id}")
    public ModelAndView detailProvince(@PathVariable Long id) {
        Pageable pageable = PageRequest.of(0,5);
        Optional<Province> province = provinceService.findById(id);

        // Kiểm tra nếu không tìm thấy Province với id được cung cấp
        if (!province.isPresent()) {
            return new ModelAndView("error_404");
        }

        // Tìm danh sách khách hàng thuộc tỉnh này
        Page <Customer> customers = customerService.findAllByProvince(province.get(),pageable);
        // Tạo ModelAndView và thêm các dữ liệu cần thiết
        ModelAndView mav = new ModelAndView("customer/list");
        mav.addObject("customers", customers);

        return mav;
    }


}
