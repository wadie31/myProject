package com.test.cnss.myProject.service;

import java.util.List;

import com.test.cnss.myProject.model.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    
    Customer getCustomerById(Long id);
    
    Customer updateCustomer(Long id, Customer customer);
    
    void deactivateCustomer(Long id);
    
    List<Customer> getAllCustomers();
}