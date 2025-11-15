package services;


import exceptions.DuplicateEntryException;
import exceptions.ResourceNotFoundException;
import interfaces.ICustomerService;
import models.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.HashMap;

public class CustomerService implements ICustomerService {
    private HashMap<Integer, Customer> customers = new HashMap<>();

    public CustomerService() {
        // sample data
        customers.put(201, new Customer(201, "Kitti", "******5173"));
        customers.put(202, new Customer(202, "Chandu", "******9491"));
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public void add(Customer c) throws DuplicateEntryException {
        if (customers.containsKey(c.getId())) throw new DuplicateEntryException("Customer exists: " + c.getId());
        customers.put(c.getId(), c);
    }

    @Override
    public Customer getById(int id) throws ResourceNotFoundException {
        Customer c = customers.get(id);
        if (c == null) throw new ResourceNotFoundException("Customer not found: " + id);
        return c;
    }

    @Override
    public void update(Customer updated) throws ResourceNotFoundException {
        if (!customers.containsKey(updated.getId())) throw new ResourceNotFoundException("Customer not found: " + updated.getId());
        customers.put(updated.getId(), updated);
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        if (!customers.containsKey(id)) throw new ResourceNotFoundException("Customer not found: " + id);
        customers.remove(id);
    }
}