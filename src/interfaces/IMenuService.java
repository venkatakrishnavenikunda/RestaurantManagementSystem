package interfaces;

import exceptions.DuplicateEntryException;
import exceptions.ResourceNotFoundException;
import models.MenuItem;

import java.util.HashSet;
import java.util.List;

public interface IMenuService {
    List<MenuItem> getAll();
    void add(MenuItem m) throws DuplicateEntryException;
    MenuItem getById(int id) throws ResourceNotFoundException;
    void update(MenuItem updated) throws ResourceNotFoundException;
    void delete(int id) throws ResourceNotFoundException;
    HashSet<String> getCategories();
}
