package com.riquelmi.apipizzeria.service;

import com.riquelmi.apipizzeria.persistence.entity.CustomerEntity;
import com.riquelmi.apipizzeria.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity findByPhone(String phone) {
        return this.customerRepository.findByPhone(phone);
    }
}
