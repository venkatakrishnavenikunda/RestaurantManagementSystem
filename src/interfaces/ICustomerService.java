package interfaces;

import exceptions.DuplicateEntryException;
import exceptions.ResourceNotFoundException;
import models.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAll();
    void add(Customer c) throws DuplicateEntryException;
    Customer getById(int id) throws ResourceNotFoundException;
    void update(Customer updated) throws ResourceNotFoundException;
    void delete(int id) throws ResourceNotFoundException;
}
