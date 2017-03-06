package com.kurachenko.proxy;

import com.kurachenko.entity.Customer;
import com.kurachenko.service.daoimpl.CustomerService;

/**
 * @author Pavel Kurachenko
 * @since 1/4/2017
 */
public class CustomerProxy extends Customer {
    private CustomerService service;
    public CustomerProxy(CustomerService service) {
        this.service = service;
    }
}
