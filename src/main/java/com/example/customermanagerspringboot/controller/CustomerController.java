package com.example.customermanagerspringboot.controller;

import com.example.customermanagerspringboot.model.Customer;
import com.example.customermanagerspringboot.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Customer>> getAllCustomerPage()
    {
        List<Customer> customers = (List<Customer>)customerService.findAll();

        if (customers.size() == 0)
        {
            return new ResponseEntity<>(customers, HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.remove(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer.setId(customerOptional.get().getId());
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
    }
    @GetMapping("/information/{id}")
    public ResponseEntity<Customer> getInforCustomer(@PathVariable("id") Long id)
    {
        Optional<Customer>optionalCustomer = customerService.findById(id);
        return new ResponseEntity<>(optionalCustomer.get(), HttpStatus.OK);
    }

}