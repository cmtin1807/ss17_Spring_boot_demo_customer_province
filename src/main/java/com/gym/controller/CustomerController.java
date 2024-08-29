package com.gym.controller;


import com.gym.model.Customer;
import com.gym.model.Province;
import com.gym.service.customer.ICustomerService;
import com.gym.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final ICustomerService customerService;
    private final IProvinceService provinceService;

    public CustomerController(ICustomerService customerService, IProvinceService provinceService) {
        this.customerService = customerService;
        this.provinceService = provinceService;
    }

    @ModelAttribute("provinces")
    public Iterable<Province> provinces() {
        return provinceService.findAll();
    }

    @GetMapping
    public ModelAndView listCustomers(@RequestParam(defaultValue = "") String search,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Customer> customers;


        if (search.trim().isEmpty()) {
            customers = customerService.findAll(pageable);
        } else {
            customers = customerService.findByFirstNameContaining(search, pageable);
        }

        ModelAndView mav = new ModelAndView("customer/list");
        mav.addObject("customers", customers);
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createCustomer() {
        ModelAndView mav = new ModelAndView("customer/create");
        mav.addObject("customer", new Customer());
        return mav;
    }

    @PostMapping("/create")
    public ModelAndView saveCustomer(Customer customer) {
        customerService.save(customer);
        ModelAndView mav = new ModelAndView("redirect:/customers");
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView mav = new ModelAndView("customer/update");
            mav.addObject("customer", customer.get());
            return mav;
        }
        return new ModelAndView("/error_404");

    }

    @PostMapping("update")
    public ModelAndView updateCustomer(Customer customer) {
        customerService.save(customer);
        ModelAndView mav = new ModelAndView("redirect:/customers");
        mav.addObject("message", "Customer updated successfully");
        return mav;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.deleteById(customer.getId());
        return "redirect:/customers";
    }

}
