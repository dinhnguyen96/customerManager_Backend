package com.example.customermanagerspringboot.service.impl;

import com.example.customermanagerspringboot.model.Customer;
import com.example.customermanagerspringboot.repository.ICustomerRepository;
import com.example.customermanagerspringboot.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerServiceImpl implements ICustomerService
{

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id)
    {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id)
    {
       customerRepository.deleteById(id);
    }

}
